package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.domain.entities.PropositionCandidatureId;
import fr.imie.poromeetlink.service.dto.CandidatureDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Interface de {@link org.springframework.stereotype.Service} pour {@link fr.imie.poromeetlink.domain.entities.Candidature}
 * @author Vanel
 */
public interface CandidatureService extends BaseService<CandidatureDto, Long> {

    CandidatureDto getByPropositionCandidature(PropositionCandidatureId id);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    List<CandidatureDto> getAll();
}
