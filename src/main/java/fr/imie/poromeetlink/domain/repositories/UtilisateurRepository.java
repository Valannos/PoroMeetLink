package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link JpaRepository} pour {@link Utilisateur}
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Cherche l'utilisateur possédant le login passé en paramètre
     *
     * @param username le login
     * @return un objet {@link Optional} pouvant contenir un {@link Utilisateur}
     */
    Optional<Utilisateur> findByUsername(String username);


}
