package fr.imie.poromeetlink.domain.repositories;

import fr.imie.poromeetlink.domain.entities.Candidat;
import fr.imie.poromeetlink.domain.entities.Employe;
import fr.imie.poromeetlink.domain.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * {@link JpaRepository} pour l'entité {@link Employe}
 */
@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    Employe getByEntreprise(Entreprise entreprise);


    /**
     * Retourne le {@link fr.imie.poromeetlink.domain.entities.Employe} associé à un compte {@link fr.imie.poromeetlink.domain.entities.Utilisateur}
     *
     * @param id Id de l'utilisateur
     * @return Un objet {@link Optional} susceptible de contenir un {@link Candidat}
     */
    Optional<Employe> findByUtilisateurId(Long id);

    /**
     * Retourne les {@link Employe}
     * @param id identifiant de l'{@link Entreprise}
     * @return une {@link List} d'{@link Employe}
     */
    List<Employe> findAllByEntrepriseId(Long id);

    /**
     * Vérifie si un employé existe pour un utilisateur donné.
     * @param id identifiant de l'{@link fr.imie.poromeetlink.domain.entities.Utilisateur}
     * @return true si existe
     */
    Boolean existsByUtilisateurId(Long id);
}
