package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Candidat;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.domain.repositories.CandidatRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CandidatDto;
import fr.imie.poromeetlink.service.mappers.CandidatMapper;
import fr.imie.poromeetlink.service.services.CandidatService;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatServiceImpl extends AbstractService<CandidatRepository> implements CandidatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidatService.class);

    @Autowired
    CandidatMapper candidatMapper;

    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public List<CandidatDto> getAll() {

        return candidatMapper.candidatToDto(repository.findAll());
    }

    @Override
    public CandidatDto getOne(Long id) throws EntryNotFound, WrongOwnerException {
        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Candidat.class));
        }
        Optional<Candidat> candidat = repository.findById(id);

        if (candidat.isPresent()) {

            if (utilisateurService.validateLoggedUser(candidat.get().getUtilisateur().getUsername()) || utilisateurService.getAuthenticatedUtilisateur().hasRole(RoleUtils.ADMINISTRATEUR_SITE)) {
                 LOGGER.info("Candidat found");
                return candidatMapper.candidatToDto(candidat.get());
            } else {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
            }
        } else

            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidat.class));
    }

    @Override
    public CandidatDto saveOne(CandidatDto dto) throws InvalidFieldException, InvalidRoleException, DuplicateEntryException {

        Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();

        if (dto != null) {

            validator(dto);

            if (!invalidFields.isEmpty()) {

                throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Candidat.class), invalidFields);
            }
            if (!nonUniqueFields.isEmpty()) {

                throw new InvalidFieldException(messageProvider.getMessage("ALREADY_EXISTS_FIELD", Candidat.class), nonUniqueFields);
            }
            if (!invalidDateFields.isEmpty()) {

                throw new InvalidFieldException(messageProvider.getMessage("INVALID_DATE", Candidat.class), nonUniqueFields);

            }
            if (!utilisateur.hasRole(RoleUtils.CANDIDAT)) {
                String[] strings = new String[]{RoleUtils.CANDIDAT};
                throw new InvalidRoleException(messageProvider.getMessage("DROITS_INSUFFISANTS", strings));
            }
            if (repository.existsCandidatByUtilisateurId(utilisateur.getId())) {
                throw new DuplicateEntryException(messageProvider.getMessage("ALREADY_EXISTS_ENTRY", Candidat.class));
            }
            Candidat candidat = candidatMapper.dtoToCandidat(dto);
            candidat.setUtilisateur(utilisateur);
            candidat = repository.save(candidat);
            dto = candidatMapper.candidatToDto(candidat);

        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Candidat.class));
        }
        return dto;
    }

    @Override
    public CandidatDto updateOne(CandidatDto dto) throws EntryNotFound, InvalidFieldException, WrongOwnerException {

        Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();
        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (!invalidFields.isEmpty()) {

                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Candidat.class), invalidFields);
                }
                if (!nonUniqueFields.isEmpty()) {

                    throw new InvalidFieldException(messageProvider.getMessage("ALREADY_EXISTS_FIELD", Candidat.class), nonUniqueFields);

                }

                if (!utilisateurService.validateLoggedUser(utilisateur.getUsername())) {

                    throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_WRITE"));
                }

                    Candidat candidat = candidatMapper.dtoToCandidat(dto);
                    candidat.setUtilisateur(utilisateur);
                    candidat = repository.save(candidat);
                    dto = candidatMapper.candidatToDto(candidat);
                    return dto;


            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidat.class));
            }

        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Candidat.class));
        }

    }

    @Override
    public Boolean delete(Long id) throws InvalidRoleException {
        if (repository.existsById(id)) {
            repository.deleteById(id);

        } else {
            throw new InvalidRoleException(messageProvider.getMessage("INVALID_FIELD", Candidat.class));
        }
        return true;
    }

    @Override
    public void validator(CandidatDto dto) {

        this.invalidFields.clear();
        this.nonUniqueFields.clear();
        Class<? extends CandidatDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.NOM:
                    if (dto.getNom() == null || dto.getNom().equals(StringUtils.EMPTY)) {
                        this.invalidFields.add(fieldName);
                    }
                    break;

                case FieldUtils.PRENOM:
                    if (dto.getPrenom() == null || dto.getPrenom().equals(StringUtils.EMPTY)) {
                        this.invalidFields.add(fieldName);
                    }
                    break;

                case FieldUtils.DATE_NAISSANCE:

                    if (dto.getDateNaissance() == null) {

                        invalidFields.add(fieldName);

                    } else {

                        if (dto.getDateNaissance().isAfter(ZonedDateTime.now()) || dto.getDateNaissance().plusYears(18).isAfter(ZonedDateTime.now())) {

                            this.invalidDateFields.add(fieldName);

                        }
                    }
                    break;


            }
        });
    }
}
