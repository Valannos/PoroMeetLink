package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.domain.repositories.CompetenceRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.mappers.CompetenceMapper;
import fr.imie.poromeetlink.service.services.CompetenceService;
import fr.imie.poromeetlink.service.services.SecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CompetenceServiceImpl extends AbstractService<CompetenceRepository> implements CompetenceService {

    @Autowired
    private CompetenceMapper competenceMapper;

    @Autowired
    private SecteurService secteurService;

    @Override
    public List<CompetenceDto> getAll() {

        return competenceMapper.competencesToDtos(repository.findAll());
    }

    @Override
    public CompetenceDto getOne(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Competence.class));
        }

        Optional<Competence> competence = repository.findById(id);

        if (competence.isPresent()) {
            return competenceMapper.competenceToDto(competence.get());
        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Competence.class));

        }
    }

    @Override
    public CompetenceDto saveOne(CompetenceDto dto) throws InvalidFieldException {

        validator(dto);

        if (!this.invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Competence.class), invalidFields);
        }

        Competence competence = repository.save(competenceMapper.dtoToCompetence(dto));

        return competenceMapper.competenceToDto(competence);
    }

    @Override
    public CompetenceDto updateOne(CompetenceDto dto) throws EntryNotFound, InvalidFieldException {

        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (invalidFields.isEmpty()) {

                    Competence competence = repository.save(competenceMapper.dtoToCompetence(dto));

                    dto = competenceMapper.competenceToDto(competence);

                } else {

                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Competence.class), invalidFields);

                }

            } else {

                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Competence.class));
            }

        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Competence.class));
        }

        return dto;
    }

    @Override
    public Boolean delete(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Competence.class));
        }

        if (repository.existsById(id)) {
            repository.deleteById(id);

        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Competence.class));
        }
        return true;
    }

    @Override
    public void validator(CompetenceDto dto) {
        this.invalidFields.clear();
        Class<? extends CompetenceDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.INTITULE:

                    if (dto.getIntitule() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.SECTEUR:
                    if (dto.getSecteur() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });
       }

    @Override
    public List<CompetenceDto> getAllByService(Long id) throws EntryNotFound {

        if (!secteurService.exists(id)) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Secteur.class));
        } else {
            List<Competence> competences = new ArrayList<>(repository.getAllBySecteurId(id));
            return competenceMapper.competencesToDtos(competences);
        }
    }
}
