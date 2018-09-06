package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Candidature;
import fr.imie.poromeetlink.domain.entities.PropositionCandidatureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} pour {@link Candidature}
 */
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

    Candidature findByPropositionCandidatureId(PropositionCandidatureId id);
}
