package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link JpaRepository} pour l'entité {@link Diplome}
 */
@Repository
public interface DiplomeRepository extends JpaRepository<Diplome, Long> {


    List<Diplome> findAllByCandidatId(Long id);

}
