package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Candidat;
import fr.imie.poromeetlink.domain.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {

    /**
     * Retourne le {@link Candidat} associé à un compte {@link fr.imie.poromeetlink.domain.entities.Utilisateur}
     *
     * @param id Id de l'utilisateur
     * @return Un objet {@link Optional} susceptible de contenir un {@link Candidat}
     */
    Optional<Candidat> findByUtilisateurId(Long id);

    /**
     *
     * @param id
     * @return
     */
    Boolean existsCandidatByUtilisateurId(Long id);

}
