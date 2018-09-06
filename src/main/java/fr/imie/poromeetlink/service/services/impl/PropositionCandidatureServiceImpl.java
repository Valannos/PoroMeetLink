package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Annonce;
import fr.imie.poromeetlink.domain.entities.Candidat;
import fr.imie.poromeetlink.domain.entities.PropositionCandidature;
import fr.imie.poromeetlink.domain.entities.PropositionCandidatureId;
import fr.imie.poromeetlink.domain.repositories.AnnonceRepository;
import fr.imie.poromeetlink.domain.repositories.CandidatRepository;
import fr.imie.poromeetlink.domain.repositories.PropositionCandidatureRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.PropositionCandidatureDto;
import fr.imie.poromeetlink.service.mappers.PropositionCandidatureMapper;
import fr.imie.poromeetlink.service.services.PropositionCandidatureService;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PropositionCandidatureServiceImpl extends AbstractService<PropositionCandidatureRepository> implements PropositionCandidatureService {


    @Autowired
    PropositionCandidatureMapper mapper;

    @Autowired
    CandidatRepository candidatRepository;

    @Autowired
    AnnonceRepository annonceRepository;

    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public List<PropositionCandidatureDto> getAll() {
        return mapper.propositionCandidatureToDto(repository.findAll());
    }

    @Override
    public Boolean acceptOrReject(PropositionCandidatureId id, Boolean isValide) {
        return null;
    }

    @Override
    public PropositionCandidatureDto getOne(PropositionCandidatureId id) throws EntryNotFound {

        Optional<PropositionCandidature> propositionCandidature = repository.findById(id);
        return propositionCandidature.map(propositionCandidature1 -> mapper.propositionCandidatureToDto(propositionCandidature1)).orElse(null);
    }

    @Override
    public PropositionCandidatureDto saveOne(PropositionCandidatureDto dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {

        if (dto == null) {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", PropositionCandidatureDto.class));
        }

        this.validator(dto);

        if (!invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", PropositionCandidature.class), invalidFields);
        }

        if (repository.existsByAnnonceIdAndCandidatId(dto.getIdAnnonce(), dto.getIdCandidat())) {
            throw new DuplicateEntryException(messageProvider.getMessage("ALREADY_EXISTS_ENTRY", PropositionCandidature.class));
        } else {
          dto = this.performSaveOrUpdate(dto);
          return dto;
        }
    }

    @Override
    public PropositionCandidatureDto updateOne(PropositionCandidatureDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {

        if (dto == null) {

            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", PropositionCandidatureDto.class));
        }

        this.validator(dto);

        if (!invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", PropositionCandidature.class), invalidFields);
        }

        if (!repository.existsByAnnonceIdAndCandidatId(dto.getIdAnnonce(), dto.getIdCandidat())) {
            throw new EntryNotFound(messageProvider.getMessage("ALREADY_EXISTS_ENTRY", PropositionCandidature.class));
        } else {
          dto = this.performSaveOrUpdate(dto);
          return dto;
        }
    }

    @Override
    public Boolean delete(PropositionCandidatureId id) throws EntryNotFound, InvalidFieldException, InvalidRoleException, WrongOwnerException {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", PropositionCandidature.class));
        }
    }

    @Override
    public void validator(PropositionCandidatureDto dto) throws NoSuchFieldException {
        this.invalidFields.clear();
        this.nonUniqueFields.clear();
        Class<? extends PropositionCandidatureDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.ACCROCHE:

                    if (dto.getAccroche() == null || dto.getAccroche().equals(StringUtils.EMPTY)) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });
    }

    private PropositionCandidatureDto performSaveOrUpdate(PropositionCandidatureDto dto) throws EntryNotFound, InsuffisantRightsException {


        Optional<Annonce> annonce = annonceRepository.findById(dto.getIdAnnonce());
        Optional<Candidat> candidat = candidatRepository.findById(dto.getIdCandidat());
        if (!annonce.isPresent()) {

            throw new EntryNotFound(messageProvider.getMessage("ALREADY_EXISTS_ENTRY", Annonce.class));

        } else if (!candidat.isPresent()) {

            throw new EntryNotFound(messageProvider.getMessage("ALREADY_EXISTS_ENTRY", Candidat.class));

        } else {

            PropositionCandidature propositionCandidature = new PropositionCandidature();
            propositionCandidature.setCandidat(candidat.get());
            propositionCandidature.setAuteurCandidat(dto.getAuteurCandidat());
            propositionCandidature.setAnnonce(annonce.get());
            propositionCandidature.setDateCreation(ZonedDateTime.now());
            propositionCandidature.setId(new PropositionCandidatureId(dto.getIdCandidat(), dto.getIdAnnonce()));
            propositionCandidature.setAcceptee(null);

            propositionCandidature = repository.save(propositionCandidature);
            return mapper.propositionCandidatureToDto(propositionCandidature);
        }

    }
}
