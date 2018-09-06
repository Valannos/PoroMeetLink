package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Annonce;
import fr.imie.poromeetlink.domain.entities.Employe;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.domain.repositories.AnnonceRepository;
import fr.imie.poromeetlink.domain.repositories.EmployeRepository;
import fr.imie.poromeetlink.domain.repositories.EntrepriseRepository;
import fr.imie.poromeetlink.domain.repositories.PropositionCandidatureRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.AnnonceDto;
import fr.imie.poromeetlink.service.mappers.AnnonceMapper;
import fr.imie.poromeetlink.service.services.AnnonceService;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AnnonceServiceImpl extends AbstractService<AnnonceRepository> implements AnnonceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnonceService.class);

    @Autowired
    AnnonceMapper mapper;

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    EntrepriseRepository entrepriseRepository;

    @Autowired
    PropositionCandidatureRepository propositionCandidatureRepository;

    @Override
    public List<AnnonceDto> getAll() {
        return mapper.annonceToDto(repository.findAll());
    }

    @Override
    public AnnonceDto getOne(Long id) throws EntryNotFound, WrongOwnerException {
        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Annonce.class));
        }
        Optional<Annonce> annonce = repository.findById(id);

        if (annonce.isPresent()) {

            if (utilisateurService.validateLoggedUser(annonce.get().getEmploye().getUtilisateur().getUsername()) || utilisateurService.getAuthenticatedUtilisateur().hasRole(RoleUtils.ADMINISTRATEUR_SITE)) {

                return mapper.annonceToDto(annonce.get());
            } else {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
            }
        } else

            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Annonce.class));
    }

    @Override
    public AnnonceDto saveOne(AnnonceDto dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {

        if (dto != null) {

            validator(dto);

            if (!invalidFields.isEmpty()) {

                throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Annonce.class), invalidFields);
            } else {
              dto = this.saveOrUpdate(dto);
            }

        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Annonce.class));
        }
        return dto;
    }

    @Override
    public AnnonceDto updateOne(AnnonceDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException {
        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (invalidFields.isEmpty()) {

                 dto = this.saveOrUpdate(dto);

                } else {
                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Employe.class), invalidFields);
                }
            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Employe.class));
            }
        } else {
            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Employe.class));
        }
        return dto;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException {

        if (repository.existsById(id)) {
            propositionCandidatureRepository.deleteAllByAnnonceId(id);

            repository.deleteById(id);
            return true;
        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Annonce.class));
        }

    }

    @Override
    public void validator(AnnonceDto dto) throws NoSuchFieldException {
        this.invalidFields.clear();
        this.nonUniqueFields.clear();
        Class<? extends AnnonceDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {
                case FieldUtils.DESCRIPTION:
                    if (dto.getValid() == null) {
                        dto.setValid(false);
                    }
                    break;
                case FieldUtils.INTITULE:
                    if (dto.getIntitule() == null) {
                        this.invalidDateFields.add(fieldName);
                    }
            }

        });

    }

    @Override
    public List<AnnonceDto> getAllByEntreprise(Long id) throws EntryNotFound, WrongOwnerException {

        if (entrepriseRepository.existsById(id)) {
            Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();
            if (utilisateur.getEmploye().getEntreprise().getId().equals(id)) {
                List<Annonce> annonces = new ArrayList<>(repository.findAllByEmployeEntrepriseId(id));
                return mapper.annonceToDto(annonces);
            } else {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
            }
        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Employe.class));
        }
    }

    @Override
    public List<AnnonceDto> getAllValidated() {

        List<Annonce> annonces = new ArrayList<>(this.repository.findAllByValid(true));
        return mapper.annonceToDto(annonces);
    }

    @Override
    public AnnonceDto toggleAnnonce(Long id) throws EntryNotFound {
        Optional<Annonce> annonce = repository.findById(id);
        if (annonce.isPresent()) {
            Annonce found = annonce.get();
            if (found.getValid().equals(Boolean.TRUE)) {
                found.setValid(false);
            } else {
               found.setValid(true);
            }
            AnnonceDto dto = mapper.annonceToDto(repository.save(found));
            LOGGER.info("Validité: "+dto.getValid());
            return dto;

        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Annonce.class));
        }
    }

    @Override
    public AnnonceDto saveOrUpdate(AnnonceDto dto) throws InvalidRoleException {
        Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();

        if (utilisateur.getEmploye() == null) {
            String[] strings = new String[]{RoleUtils.ADMINISTRATEUR_COMPTE, RoleUtils.GERANT, RoleUtils.RECRUTEUR};
            throw new InvalidRoleException(messageProvider.getMessage("DROITS_INSUFFISANTS", strings));
        } else {
            Annonce annonce = mapper.dtoToAnnonce(dto);
            if (dto.getDateFin() == null) {
                annonce.setDateFin(null);
            }
            annonce.setEmploye(utilisateur.getEmploye());
            annonce.setDateCreation(ZonedDateTime.now());
            annonce = repository.save(annonce);
            dto = mapper.annonceToDto(annonce);
            return dto;
        }
    }
}
