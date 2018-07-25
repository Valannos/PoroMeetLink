package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} pour {@link Candidature}
 */
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
}
