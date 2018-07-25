package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    Set<Annonce> findAllByEmployeEntrepriseId(Long id);

    Set<Annonce> findAllByValid(Boolean validity);
}
