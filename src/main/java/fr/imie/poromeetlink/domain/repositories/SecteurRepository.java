package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Secteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link JpaRepository} pour {@link Secteur}
 */
@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {

    Optional<Secteur> findByLibelle(String libelle);

}
