package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.CompetenceAnnonce;
import fr.imie.poromeetlink.domain.entities.CompetenceAnnonceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceAnnonceRepository extends JpaRepository<CompetenceAnnonce, CompetenceAnnonceId> {
}
