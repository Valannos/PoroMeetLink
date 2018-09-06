package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.*;
import fr.imie.poromeetlink.domain.repositories.CandidatRepository;
import fr.imie.poromeetlink.domain.repositories.CompetenceCandidatRepository;
import fr.imie.poromeetlink.domain.repositories.CompetenceRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CompetenceCandidatDto;
import fr.imie.poromeetlink.service.mappers.CompetenceCandidatMapper;
import fr.imie.poromeetlink.service.services.CompetenceCandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CompetenceCandidatServiceImpl extends AbstractService<CompetenceCandidatRepository> implements CompetenceCandidatService {

    @Autowired
    CompetenceCandidatMapper mapper;

    @Autowired
    CompetenceRepository competenceRepository;

    @Autowired
    CandidatRepository candidatRepository;


    @Override
    public List<CompetenceCandidatDto> getAll() {
        List<CompetenceCandidat> competenceCandidats = repository.findAll();
        return mapper.competenceCandidatsToDtos(competenceCandidats);
    }

    @Override
    public CompetenceCandidatDto getOne(CompetenceCandidatId id) throws EntryNotFound, WrongOwnerException {
        Optional<CompetenceCandidat> competenceCandidat = repository.findById(id);
        if (!competenceCandidat.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", CompetenceCandidat.class));
        } else {

            Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();
            if (utilisateur.hasRole(RoleUtils.ADMINISTRATEUR_SITE) || utilisateur.getCandidat().getId().equals(competenceCandidat.get().getCandidat().getId())) {
                return mapper.competenceCandidatToDto(competenceCandidat.get());
            } else {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
            }
        }
    }

    @Override
    public CompetenceCandidatDto saveOne(CompetenceCandidatDto dto) throws InvalidFieldException, NoSuchFieldException, DuplicateEntryException, EntryNotFound, WrongOwnerException {

        validator(dto);
        if (!invalidFields.isEmpty()) {
            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD"), invalidFields);
        }

        Optional<Candidat> candidat = candidatRepository.findById(dto.getIdCandidat());

        if (!candidat.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidat.class));
        }

        Optional<Competence> competence = competenceRepository.findById(dto.getCompetence().getId());

        if (!competence.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Competence.class));
        }
        CompetenceCandidat competenceCandidat = new CompetenceCandidat();
        competenceCandidat.setNiveau(dto.getNiveau());
        competenceCandidat.setCandidat(candidat.get());
        competenceCandidat.setCompetence(competence.get());
        competenceCandidat.setId(new CompetenceCandidatId(competence.get().getId(), candidat.get().getId()));
        return mapper.competenceCandidatToDto(repository.save(competenceCandidat));
    }

    @Override
    public CompetenceCandidatDto updateOne(CompetenceCandidatDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException {

        validator(dto);
        if (!invalidFields.isEmpty()) {
            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD"), invalidFields);
        }

        if (!repository.existsById(dto.getId())) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", CompetenceCandidat.class));
        } else {

            CompetenceCandidat competenceCandidat = mapper.dtoToCompetenceCandidat(dto);
           return mapper.competenceCandidatToDto(repository.save(competenceCandidat));
        }

    }

    @Override
    public Boolean delete(CompetenceCandidatId id) throws EntryNotFound, InvalidFieldException, InvalidRoleException, WrongOwnerException {

        Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();

        if (utilisateur.hasRole(RoleUtils.CANDIDAT)) {
            if (utilisateur.getCandidat().getId().equals(id.getIdCandidat())) {

                if (repository.existsById(id)) {
                    repository.deleteById(id);
                    return true;
                } else {
                    throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", CompetenceCandidat.class));
                }
            } else {
                throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_WRITE"));
            }
        }
       return false;
    }

    @Override
    public void validator(CompetenceCandidatDto dto) throws NoSuchFieldException {
        this.invalidFields.clear();
        Class<? extends CompetenceCandidatDto> clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.NIVEAU:

                    if (dto.getNiveau() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });
    }

    @Override
    public List<CompetenceCandidatDto> getAllByCandidat(Long id) throws EntryNotFound {

        Optional<Candidat> candidat = candidatRepository.findById(id);

        if (!candidat.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidat.class));
        } else {

            List<CompetenceCandidat> competenceCandidats = repository.findAllByCandidat_Id(id);
            return mapper.competenceCandidatsToDtos(competenceCandidats);

        }

    }

    @Override
    public Boolean checkExisteByCompetence(Long id) throws EntryNotFound {
        return repository.existsByCompetence_Id(id);
    }
}
