package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.CompetenceCandidat;
import fr.imie.poromeetlink.domain.entities.CompetenceCandidatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceCandidatRepository extends JpaRepository<CompetenceCandidat, CompetenceCandidatId> {

    List<CompetenceCandidat> findAllByCandidat_Id(Long id);

    Boolean existsByCompetence_Id(Long id);

}
