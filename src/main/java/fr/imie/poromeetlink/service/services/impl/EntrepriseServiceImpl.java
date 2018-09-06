package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Entreprise;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.domain.repositories.EntrepriseRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.service.dto.EntrepriseDto;
import fr.imie.poromeetlink.service.mappers.EntrepriseMapper;
import fr.imie.poromeetlink.service.services.EmployeService;
import fr.imie.poromeetlink.service.services.EntrepriseService;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseServiceImpl extends AbstractService<EntrepriseRepository> implements EntrepriseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntrepriseService.class);

    @Autowired
    private EntrepriseMapper entrepriseMapper;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public List<EntrepriseDto> getAll() {
        return entrepriseMapper.entrepriseToDto(repository.findAll());
    }

    @Override
    public EntrepriseDto getOne(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Entreprise.class));
        }

        Optional<Entreprise> entreprise = repository.findById(id);

        if (!entreprise.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Entreprise.class));
        } else {

            return entrepriseMapper.entrepriseToDto(entreprise.get());

        }
    }

    @Override
    public EntrepriseDto saveOne(EntrepriseDto dto) throws InvalidFieldException {
        validator(dto);

        if (!invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Entreprise.class), invalidFields);

        }

        Entreprise employe = repository.save(entrepriseMapper.dtoToEntreprise(dto));

        return entrepriseMapper.entrepriseToDto(employe);
    }

    @Override
    public EntrepriseDto updateOne(EntrepriseDto dto) throws InvalidFieldException, EntryNotFound {
        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (invalidFields.isEmpty()) {

                    Entreprise employe = repository.save(entrepriseMapper.dtoToEntreprise(dto));

                    dto = entrepriseMapper.entrepriseToDto(employe);
                } else {
                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Entreprise.class), invalidFields);
                }
            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Entreprise.class));
            }
        } else {
            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Entreprise.class));
        }
        return dto;
    }

    @Override
    public Boolean delete(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Entreprise.class));
        } else {

            if (repository.existsById(id)) {

                repository.deleteById(id);

            } else {

                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Entreprise.class));

            }
        }
        return true;
    }

    @Override
    public void validator(EntrepriseDto dto) {
        LOGGER.info("Entreprise à enregister: " + dto.toString());
        Class<? extends EntrepriseDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.SIRET:
                    if (StringUtils.isBlank(dto.getSiret())) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.ADRESSE:
                    if (StringUtils.isBlank(dto.getAdresse())) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.TELEPHONE:
                    if (StringUtils.isBlank(dto.getTelephone())) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });
    }

    @Override
    public EntrepriseDto getEntrepriseByUtilisateur() throws EntryNotFound {

            Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();
        if (employeService.existsByUtilisateur(utilisateur.getId())) {
            Hibernate.initialize(utilisateur.getEmploye().getEntreprise());
            return entrepriseMapper.entrepriseToDto(utilisateur.getEmploye().getEntreprise());
        } else {
            return null;
        }
    }
}
