package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.TypeDiplome;
import fr.imie.poromeetlink.domain.repositories.TypeDiplomeRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.TypeDiplomeDto;
import fr.imie.poromeetlink.service.mappers.TypeDiplomeMapper;
import fr.imie.poromeetlink.service.services.TypeDiplomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
public class TypeDiplomeServiceImpl extends AbstractService<TypeDiplomeRepository> implements TypeDiplomeService {

    @Autowired
    TypeDiplomeMapper mapper;

    @Override
    public List<TypeDiplomeDto> getAll() {
        return mapper.typeDiplomesToDtos(repository.findAll());
    }

    @Override
    public TypeDiplomeDto getOne(Long id) throws EntryNotFound {

        if (!repository.existsById(id)) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", TypeDiplome.class));
        } else {
            return mapper.typeDiplomeToDto(repository.getOne(id));
        }
    }

    @Override
    public TypeDiplomeDto saveOne(TypeDiplomeDto dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {

        validator(dto);
        if (!this.invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Competence.class), invalidFields);
        } else {
            TypeDiplome typeDiplome = mapper.dtoToTypeDiplome(dto);
            return mapper.typeDiplomeToDto(repository.save(typeDiplome));
        }
    }

    @Override
    public TypeDiplomeDto updateOne(TypeDiplomeDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException {
        if (dto != null) {
            if (repository.existsById(dto.getId())) {
                validator(dto);
                if (invalidFields.isEmpty()) {
                    TypeDiplome type = repository.save(mapper.dtoToTypeDiplome(dto));
                    dto = mapper.typeDiplomeToDto(type);
                } else {
                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", TypeDiplome.class), invalidFields);
                }
            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", TypeDiplome.class));
            }
        } else {
            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", TypeDiplome.class));
        }
        return dto;
    }

    @Override
    public Boolean delete(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Competence.class));
        }
        if (!repository.existsById(id)) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", TypeDiplome.class));
        } else {
            repository.deleteById(id);
            return true;
        }
    }

    @Override
    public void validator(TypeDiplomeDto dto) throws NoSuchFieldException {

        this.invalidFields.clear();
        Class<? extends TypeDiplomeDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.INTITULE:

                    if (dto.getIntitule() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });

    }
}
