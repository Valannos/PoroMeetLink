package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.PropositionCandidature;
import fr.imie.poromeetlink.domain.entities.PropositionCandidatureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link Repository} pour {@link PropositionCandidature}
 */
@Repository
public interface PropositionCandidatureRepository extends JpaRepository<PropositionCandidature, PropositionCandidatureId> {

    Boolean existsByAnnonceIdAndCandidatId(Long idAnnonce, Long idCandidat);

    List<PropositionCandidature> findAllByAnnonceId(Long id);

    void deleteAllByAnnonceId(Long id);
}
