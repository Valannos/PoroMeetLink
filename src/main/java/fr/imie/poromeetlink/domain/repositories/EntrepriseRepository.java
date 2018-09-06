package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link JpaRepository} pour {@link Entreprise}
 */
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

    Optional<Entreprise> findBySiret(String siret);

}
