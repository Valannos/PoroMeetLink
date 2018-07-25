package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.domain.entities.PropositionCandidatureId;
import fr.imie.poromeetlink.service.dto.PropositionCandidatureDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PropositionCandidatureService extends BaseService<PropositionCandidatureDto, PropositionCandidatureId> {

    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    @Override
    List<PropositionCandidatureDto> getAll();

    Boolean acceptOrReject(PropositionCandidatureId id, Boolean isValide);

}
