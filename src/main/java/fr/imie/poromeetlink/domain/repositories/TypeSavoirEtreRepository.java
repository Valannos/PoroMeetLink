package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.TypeSavoirEtre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link JpaRepository} pour {@link TypeSavoirEtre}
 */

@Repository
public interface TypeSavoirEtreRepository extends JpaRepository<TypeSavoirEtre, Long> {

    TypeSavoirEtre findByIntitule(String intitule);
}
