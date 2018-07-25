package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * {@link JpaRepository} pour l'entité {@link Experience}
 */
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    Optional<Experience> findByIntitule(String intitule);
}
