package fr.imie.poromeetlink.service.services.impl;

import fr.imie.poromeetlink.domain.entities.Candidat;
import fr.imie.poromeetlink.domain.entities.Candidature;
import fr.imie.poromeetlink.domain.entities.PropositionCandidatureId;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.domain.repositories.CandidatureRepository;
import fr.imie.poromeetlink.outils.constantes.FieldUtils;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CandidatureDto;
import fr.imie.poromeetlink.service.mappers.CandidatureMapper;
import fr.imie.poromeetlink.service.services.CandidatureService;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation for {@link CandidatureService }
 * @author Vanel
 */
@Service
public class CandidatureServiceImpl extends AbstractService<CandidatureRepository> implements CandidatureService {

    private final CandidatureMapper candidatureMapper;

    private final UtilisateurService utilisateurService;

    @Autowired
    public CandidatureServiceImpl(CandidatureMapper candidatureMapper, UtilisateurService utilisateurService) {
        this.candidatureMapper = candidatureMapper;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public List<CandidatureDto> getAll() {
        return candidatureMapper.candidatureToDto(repository.findAll());
    }

    @Override
    public CandidatureDto getOne(Long id) throws EntryNotFound, WrongOwnerException {
        CandidatureDto candidatureDto = null;
        if (id == null) {
            throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Candidature.class));
        }

        Optional<Candidature> candidature = repository.findById(id);
        if (!candidature.isPresent()) {
            throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Candidature.class));
        } else {

           Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();
          if (utilisateur.hasRole(RoleUtils.CANDIDAT) && candidature.get().getPropositionCandidature().getCandidat().equals(utilisateur.getCandidat())) {
              candidatureDto = candidatureMapper.candidatureToDto(candidature.get());
          } else if (utilisateur.hasRole(RoleUtils.ADMINISTRATEUR_SITE)) {
              candidatureDto = candidatureMapper.candidatureToDto(candidature.get());
          } else if (candidature.get().getPropositionCandidature().getAnnonce().getEmploye().equals(utilisateur.getEmploye())) {
              candidatureDto = candidatureMapper.candidatureToDto(candidature.get());
          } else {
              throw new WrongOwnerException(messageProvider.getMessage("WRONG_OWNER_READ"));
          }
        }
        return candidatureDto;
    }

    @Override
    public CandidatureDto saveOne(CandidatureDto dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {

        if (dto == null) {
            throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Candidat.class));

        }

        validator(dto);
        if (!invalidFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("INVALID_FIELD", Candidat.class), invalidFields);
        }
        if (!nonUniqueFields.isEmpty()) {

            throw new InvalidFieldException(messageProvider.getMessage("ALREADY_EXISTS_FIELD", Candidat.class), nonUniqueFields);
        }

        Candidature candidature = candidatureMapper.dtoToCandidature(dto);
        candidature.setDateCreation(ZonedDateTime.now());
        candidature.setEstCloturee(false);

        return null;

    }

    @Override
    public CandidatureDto updateOne(CandidatureDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException, WrongOwnerException {
        if (repository.existsById(id)) {
            repository.deleteById(id);

        } else {
            throw new InvalidRoleException(messageProvider.getMessage("INVALID_FIELD", Candidat.class));
        }
        return true;
    }

    @Override
    public void validator(CandidatureDto dto) {
        this.invalidFields.clear();
        this.nonUniqueFields.clear();
        Class clazz = dto.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        fields.forEach((Field field) -> {

            String fieldName = field.getName();

            switch (fieldName) {

                case FieldUtils.PROPOSITION_CANDIDATURE:

                    if (dto.getPropositionCandidature() == null) {
                        this.invalidFields.add(fieldName);
                    }
                    break;
            }
        });
    }

    @Override
    public CandidatureDto getByPropositionCandidature(PropositionCandidatureId id) {
        return candidatureMapper.candidatureToDto(repository.findByPropositionCandidatureId(id));
    }
}
