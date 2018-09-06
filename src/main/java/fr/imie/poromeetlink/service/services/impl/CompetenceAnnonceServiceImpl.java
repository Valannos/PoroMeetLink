package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Annonce;
import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.CompetenceAnnonce;
import fr.imie.poromeetlink.domain.entities.CompetenceAnnonceId;
import fr.imie.poromeetlink.domain.repositories.CompetenceAnnonceRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.WrongOwnerException;
import fr.imie.poromeetlink.service.dto.CompetenceAnnonceDto;
import fr.imie.poromeetlink.service.mappers.AnnonceMapper;
import fr.imie.poromeetlink.service.mappers.CompetenceAnnonceMapper;
import fr.imie.poromeetlink.service.mappers.CompetenceMapper;
import fr.imie.poromeetlink.service.services.AnnonceService;
import fr.imie.poromeetlink.service.services.CompetenceAnnonceService;
import fr.imie.poromeetlink.service.services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
public class CompetenceAnnonceServiceImpl extends AbstractService<CompetenceAnnonceRepository> implements CompetenceAnnonceService {

    @Autowired
    CompetenceAnnonceMapper mapper;

    @Autowired
    CompetenceService competenceService;

    @Autowired
    CompetenceMapper competenceMapper;

    @Autowired
    AnnonceMapper annonceMapper;

    @Autowired
    AnnonceService annonceService;

    @Override
    public List<CompetenceAnnonceDto> getAll() {
        return mapper.competenceAnnonceToDto(repository.findAll());
    }

    @Override
    public CompetenceAnnonceDto getOne(CompetenceAnnonceId id) throws EntryNotFound {

        if (repository.existsById(id)) {
            return mapper.competenceAnnonceToDto(repository.getOne(id));
        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", CompetenceAnnonce.class));
        }
    }

    @Override
    public Boolean delete(CompetenceAnnonceId id) throws EntryNotFound {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", CompetenceAnnonce.class));
        }
    }

    @Override
    public CompetenceAnnonceDto saveOne(CompetenceAnnonceDto dto) throws InvalidFieldException, WrongOwnerException, EntryNotFound {

        validator(dto);

        if (!this.invalidDateFields.isEmpty()) {
            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", CompetenceAnnonce.class), invalidFields);
        }

        Annonce annonce = annonceMapper.dtoToAnnonce(annonceService.getOne(dto.getIdAnnonce()));
        Competence competence = competenceMapper.dtoToCompetence(competenceService.getOne(dto.getCompetence().getId()));

        CompetenceAnnonce competenceAnnonce = new CompetenceAnnonce();
        competenceAnnonce.setCompetence(competence);
        competenceAnnonce.setNiveauRequis(dto.getNiveauRequis());
        competenceAnnonce.setAnnonce(annonce);
        CompetenceAnnonceId id = new CompetenceAnnonceId();
        id.setIdAnnonce(annonce.getId());
        id.setIdCompetence(competence.getId());
        competenceAnnonce.setId(id);

        return mapper.competenceAnnonceToDto(repository.save(competenceAnnonce));
    }

    @Override
    public CompetenceAnnonceDto updateOne(CompetenceAnnonceDto dto) throws EntryNotFound, InvalidFieldException {
        validator(dto);
        if (!invalidFields.isEmpty()) {
            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD"), invalidFields);
        }

        if (!repository.existsById(dto.getId())) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", CompetenceAnnonce.class));
        } else {

            CompetenceAnnonce competenceAnnonce = mapper.dtoToCompetenceAnnonce(dto);
            return mapper.competenceAnnonceToDto(repository.save(competenceAnnonce));
        }
    }

    @Override
    public void validator(CompetenceAnnonceDto dto) {
        this.invalidFields.clear();
        Class<? extends CompetenceAnnonceDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {
                case FieldUtils.NIVEAU_REQUIS:
                    if (dto.getNiveauRequis() == null) {
                        this.invalidDateFields.add(fieldName);
                    }
            }
        });
    }
}
