package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.TypeDiplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDiplomeRepository extends JpaRepository<TypeDiplome, Long> {
}
