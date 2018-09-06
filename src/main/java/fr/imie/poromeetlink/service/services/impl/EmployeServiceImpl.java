package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Employe;
import fr.imie.poromeetlink.domain.entities.Entreprise;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.domain.repositories.EmployeRepository;
import fr.imie.poromeetlink.domain.repositories.EntrepriseRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.outils.exceptions.WrongOwnerException;
import fr.imie.poromeetlink.service.dto.EmployeDto;
import fr.imie.poromeetlink.service.mappers.EmployeMapper;
import fr.imie.poromeetlink.service.services.EmployeService;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl extends AbstractService<EmployeRepository> implements EmployeService {

    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeService.class);

    @Autowired
    EmployeMapper mapper;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    EntrepriseRepository entrepriseRepository;

    @Override
    public List<EmployeDto> getAll() {
        return mapper.employeToDto(repository.findAll());
    }

    @Override
    public EmployeDto getOne(Long id) throws EntryNotFound, WrongOwnerException {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Employe.class));
        }

        Optional<Employe> employe = repository.findById(id);

        if (employe.isPresent()) {

            if (utilisateurService.validateLoggedUser(employe.get().getUtilisateur().getUsername()) || utilisateurService.getAuthenticatedUtilisateur().hasRole(RoleUtils.ADMINISTRATEUR_SITE)) {
                return mapper.employeToDto(employe.get());
            } else {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
            }

        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Employe.class));

        }
    }

    @Override
    public EmployeDto saveOne(EmployeDto dto) throws InvalidFieldException, WrongOwnerException, EntryNotFound {

        validator(dto);

        if (!invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Employe.class), invalidFields);

        }

        LOGGER.info("Employe à sauver:" + dto);
        Employe employe = mapper.dtoToEmploye(dto);
        employe.setEntreprise(entrepriseRepository.getOne(dto.getEntrepriseId()));
        employe.setUtilisateur(utilisateurService.getAuthenticatedUtilisateur());
        employe = repository.save(employe);

        return mapper.employeToDto(employe);
    }

    @Override
    public EmployeDto updateOne(EmployeDto dto) throws EntryNotFound, InvalidFieldException {

        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (invalidFields.isEmpty()) {

                    Employe employe = mapper.dtoToEmploye(dto);
                    employe.setEntreprise(entrepriseRepository.getOne(dto.getEntrepriseId()));
                    employe.setUtilisateur(utilisateurService.getAuthenticatedUtilisateur());
                    employe = repository.save(employe);
                    dto = mapper.employeToDto(employe);
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
    public Boolean delete(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Employe.class));
        } else {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Employe.class));
            }
        }
        return true;
    }

    @Override
    public void validator(EmployeDto dto) {

        Class<? extends EmployeDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.ENTREPRISE:
                    if (dto.getEntrepriseId() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });

    }

    @Override
    public List<EmployeDto> getAllEmployesByEntreprise(Long id) throws EntryNotFound, WrongOwnerException {

        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        if (!entreprise.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Entreprise.class));
        } else {

            Utilisateur utilisateur = this.utilisateurService.getAuthenticatedUtilisateur();

            if (!utilisateur.getEmploye().getEntreprise().equals(entreprise.get())) {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
            } else {
                List<Employe> employes = repository.findAllByEntrepriseId(id);
                return mapper.employeToDto(employes);
            }
        }
    }

    @Override
    public EmployeDto getByUtilisateur(Long id) {

        Optional<Employe> optional = repository.findByUtilisateurId(id);
        return optional.map(employe -> mapper.employeToDto(employe)).orElse(null);
     }

    @Override
    public Boolean existsByUtilisateur(Long id) {
        return repository.existsByUtilisateurId(id);
    }
}
