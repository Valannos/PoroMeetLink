package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Entreprise;
import fr.imie.poromeetlink.domain.entities.Experience;
import fr.imie.poromeetlink.domain.repositories.ExperienceRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.service.dto.ExperienceDto;
import fr.imie.poromeetlink.service.mappers.ExperienceMapper;
import fr.imie.poromeetlink.service.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl extends AbstractService<ExperienceRepository> implements ExperienceService {

    @Autowired
    ExperienceMapper mapper;

    @Override
    public List<ExperienceDto> getAll() {
        return mapper.experienceToDto(repository.findAll());
    }

    @Override
    public ExperienceDto getOne(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Entreprise.class));
        }

        Optional<Experience> experience = repository.findById(id);
        if (!experience.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Experience.class));
        } else {
            return mapper.experienceToDto(experience.get());
        }

    }

    @Override
    public ExperienceDto saveOne(ExperienceDto dto) throws InvalidFieldException, NoSuchFieldException {

        validator(dto);

        if (!invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Experience.class), invalidFields);

        }

        Experience experience = repository.save(mapper.dtoToExperience(dto));

        return mapper.experienceToDto(experience);
    }


    @Override
    public ExperienceDto updateOne(ExperienceDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException {
        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (invalidFields.isEmpty()) {

                    Experience experience = repository.save(mapper.dtoToExperience(dto));

                    dto = mapper.experienceToDto(experience);
                } else {
                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Experience.class), invalidFields);
                }
            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Experience.class));
            }
        } else {
            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Experience.class));
        }
        return dto;
    }

    @Override
    public Boolean delete(Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException {
        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Experience.class));
        } else {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Experience.class));
            }
        }
        return true;
    }

    @Override
    public void validator(ExperienceDto dto) throws NoSuchFieldException {

        Class clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.CANDIDAT:
                    if (dto.getCandidat() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.INTITULE:
                    if (dto.getIntitule() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.DATE_DEBUT:
                    if (dto.getDateDebut() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.DATE_FIN:
                    if (dto.getDateFin() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;

                case FieldUtils.STRUCTURE:
                    if (dto.getStructure() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.VILLE:
                    if (dto.getVille() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });
    }

}
