package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.service.dto.CandidatDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Interface de {@link org.springframework.stereotype.Service} pour {@link fr.imie.poromeetlink.domain.entities.Candidat}
 */
public interface CandidatService extends BaseService<CandidatDto, Long> {

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    List<CandidatDto> getAll();

}
