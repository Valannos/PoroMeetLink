package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.SavoirEtre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link JpaRepository} pour {@link SavoirEtre}
 */

@Repository
public interface SavoirEtreRepository extends JpaRepository<SavoirEtre, Long> {
}
