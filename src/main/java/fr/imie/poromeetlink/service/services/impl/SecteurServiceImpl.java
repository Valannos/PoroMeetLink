package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.domain.repositories.SecteurRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.service.mappers.SecteurMapper;
import fr.imie.poromeetlink.service.services.SecteurService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SecteurServiceImpl extends AbstractService<SecteurRepository> implements SecteurService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecteurService.class);

    @Autowired
    private SecteurMapper secteurMapper;

    private SecteurDto secteurDto = null;


    @Override
    public List<SecteurDto> getAll() {

        List<SecteurDto> secteurDtos = new ArrayList<>();

        repository.findAll().forEach(secteur ->

                secteurDtos.add(secteurMapper.map(secteur, SecteurDto.class)));


        return secteurDtos;
    }

    @Override
    public SecteurDto getOne(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Secteur.class));
        }

        Optional<Secteur> secteur = repository.findById(id);

        if (secteur.isPresent()) {

            return secteurMapper.map(secteur.get(), SecteurDto.class);
        } else

            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Secteur.class));

    }

    @Override
    public SecteurDto saveOne(SecteurDto dto) throws InvalidFieldException {

        if (dto != null) {

            validator(dto);

            if (!invalidFields.isEmpty()) {

                throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Secteur.class), invalidFields);
            }
            if (!nonUniqueFields.isEmpty()) {

                throw new InvalidFieldException(messageProvider.getMessage("ALREADY_EXISTS_FIELD", Secteur.class), nonUniqueFields);
            }

            Secteur secteur = secteurMapper.map(dto, Secteur.class);
            secteurDto = secteurMapper.map(repository.save(secteur), SecteurDto.class);

        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Secteur.class));
        }
        return secteurDto;
    }

    @Override
    public SecteurDto updateOne(SecteurDto dto) throws InvalidFieldException, EntryNotFound {
        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (!invalidFields.isEmpty()) {

                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Secteur.class), invalidFields);
                }
                if (!nonUniqueFields.isEmpty()) {

                    throw new InvalidFieldException(messageProvider.getMessage("ALREADY_EXISTS_FIELD", Secteur.class), nonUniqueFields);

                }

            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Secteur.class));
            }

        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Secteur.class));
        }
        Secteur competence = repository.save(secteurMapper.map(dto, Secteur.class));
        dto = secteurMapper.map(competence, SecteurDto.class);

        return dto;
    }

    @Override
    public Boolean delete(Long id) throws EntryNotFound {
        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Secteur.class));
        } else {
            if (repository.existsById(id)) {
                repository.deleteById(id);

            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Secteur.class));
            }
        }
        return true;
    }

    @Override
    public Boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void validator(SecteurDto dto) {
    this.invalidFields.clear();
    this.nonUniqueFields.clear();
        Class clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.LIBELLE:

                    if (dto.getLibelle() == null || dto.getLibelle().equals(StringUtils.EMPTY)) {
                        this.invalidFields.add(fieldName);
                    }
                    Optional<Secteur> optionalSecteur = repository.findByLibelle(dto.getLibelle());
                    if (optionalSecteur.isPresent()) {
                        this.nonUniqueFields.add(fieldName);
                    }
                    break;
            }
        });
    }

    @Override
    public SecteurDto getByLibelle(String libelle) {

        return secteurMapper.map(repository.findByLibelle(libelle).get(), SecteurDto.class);
    }
}
