package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.domain.entities.CompetenceCandidatId;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CompetenceCandidatDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CompetenceCandidatService extends BaseService<CompetenceCandidatDto, CompetenceCandidatId> {

    /**
     * Retourne les {@link fr.imie.poromeetlink.domain.entities.CompetenceCandidat} d'un {@link fr.imie.poromeetlink.domain.entities.Candidat}
     * @param id identifiant du {@link fr.imie.poromeetlink.domain.entities.Candidat}
     * @return une {@link List} d'objet {@link CompetenceCandidatDto}
     */
    List<CompetenceCandidatDto> getAllByCandidat(Long id) throws EntryNotFound;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    Boolean checkExisteByCompetence(Long id) throws EntryNotFound;
}
