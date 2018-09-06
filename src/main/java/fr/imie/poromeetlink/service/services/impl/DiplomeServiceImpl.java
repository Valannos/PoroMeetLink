package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Candidat;
import fr.imie.poromeetlink.domain.entities.Diplome;
import fr.imie.poromeetlink.domain.entities.TypeDiplome;
import fr.imie.poromeetlink.domain.repositories.CandidatRepository;
import fr.imie.poromeetlink.domain.repositories.DiplomeRepository;
import fr.imie.poromeetlink.domain.repositories.TypeDiplomeRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.service.dto.DiplomeDto;
import fr.imie.poromeetlink.service.mappers.DiplomeMapper;
import fr.imie.poromeetlink.service.services.DiplomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DiplomeServiceImpl extends AbstractService<DiplomeRepository> implements DiplomeService {

    @Autowired
    DiplomeMapper mapper;

    @Autowired
    CandidatRepository candidatRepository;

    @Autowired
    TypeDiplomeRepository typeDiplomeRepository;

    @Override
    public List<DiplomeDto> getAll() {
        return mapper.diplomeToDto(repository.findAll());
    }

    @Override
    public DiplomeDto getOne(Long id) throws EntryNotFound {

        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Diplome.class));
        }

        Optional<Diplome> diplome = repository.findById(id);
        if (diplome.isPresent()) {
            return mapper.diplomeToDto(diplome.get());
        } else {

            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Diplome.class));
        }
    }

    @Override
    public DiplomeDto saveOne(DiplomeDto dto) throws InvalidFieldException, EntryNotFound {

        if (dto != null) {

            validator(dto);

            if (!this.invalidFields.isEmpty()) {

                throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Diplome.class), invalidFields);
            } else {

                Optional<Candidat> candidat = candidatRepository.findById(dto.getIdCandidat());
                Optional<TypeDiplome> typeDiplome = typeDiplomeRepository.findById(dto.getTypeDiplome().getId());

                if (!candidat.isPresent()) {

                    throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidat.class));

                } else if (!typeDiplome.isPresent()) {

                    throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidat.class));

                } else {

                    Diplome diplome = new Diplome();
                    diplome.setCandidat(candidat.get());
                    diplome.setTypeDiplome(typeDiplome.get());
                    diplome.setIntitule(dto.getIntitule());
                    diplome.setEtablissement(dto.getEtablissement());
                    diplome.setDiplomeEnum(dto.getDiplomeEnum());
                    diplome.setAnneeObtention(dto.getAnneeObtention());
                    dto = mapper.diplomeToDto(repository.save(diplome));
                    return dto;
                }
            }
        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", DiplomeDto.class));
        }
    }

    @Override
    public DiplomeDto updateOne(DiplomeDto dto) throws InvalidFieldException, EntryNotFound {
        if (dto != null) {

            if (repository.existsById(dto.getId())) {

                validator(dto);

                if (invalidFields.isEmpty()) {

                    Optional<Candidat> candidat = candidatRepository.findById(dto.getIdCandidat());

                    if (candidat.isPresent()) {
                        Diplome diplome = mapper.dtoToDiplome(dto);
                        diplome.setCandidat(candidat.get());
                        dto = mapper.diplomeToDto(repository.save(diplome));
                    } else {
                        throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidat.class));
                    }
                } else {

                    throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Diplome.class), invalidFields);

                }

            } else {
                throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Diplome.class));
            }

        } else {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Diplome.class));
        }

        return dto;
    }


    @Override
    public Boolean delete(Long id) throws EntryNotFound {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {

            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Diplome.class));
        }
        return true;
    }

    @Override
    public void validator(DiplomeDto dto) {

        Class<? extends DiplomeDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.ANNEE_OBTENTION:
                    if (dto.getAnneeObtention() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.CANDIDAT:
                    if (dto.getIdCandidat() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.ETABLISSEMENT:
                    if (dto.getEtablissement() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.INTITULE:
                    if (dto.getIntitule() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.DIPLOME_ENUM:
                    if (dto.getDiplomeEnum() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
                case FieldUtils.TYPE_DIPLOME_ENUM:
                    if (dto.getTypeDiplome() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });

    }

    @Override
    public List<DiplomeDto> getAllByCandidatId(Long id) {
        return mapper.diplomeToDto(repository.findAllByCandidatId(id));
    }
}
