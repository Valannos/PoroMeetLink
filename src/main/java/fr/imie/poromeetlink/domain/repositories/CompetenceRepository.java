package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * {@link JpaRepository} pour l'entité {@link Competence}
 */
@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    Competence findByIntitule(String libelle);

    Set<Competence> getAllBySecteurId(Long id);
}
