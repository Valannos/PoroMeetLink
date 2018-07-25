package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.domain.entities.Employe;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.WrongOwnerException;
import fr.imie.poromeetlink.service.dto.EmployeDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface EmployeService extends BaseService<EmployeDto, Long> {

    /**
     * Retourne la liste de tous les employés associés à une entreprise
     * @param id l'id de l'entreprise
     * @return Une {@link List} d'objets {@link EmployeDto}
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_COMPTE') or hasRole('ROLE_GERANT')")
   List<EmployeDto> getAllEmployesByEntreprise(Long id) throws EntryNotFound, WrongOwnerException;

    /**
     * Retourne l'employé associé à un utilisateur
     * @param id l'id associé à un {@link Utilisateur}
     * @return un objet {@link EmployeDto} si il existe, sinon null
     */
   @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_COMPTE') or hasRole('ROLE_GERANT')")
   EmployeDto getByUtilisateur(Long id);

    /**
     * Vérifie si un {@link Employe} existe pour un {@link Utilisateur} donné
     * @param id identifiant de l'utilisateur
     * @return true si un employé a été trouvé.
     */
   Boolean existsByUtilisateur(Long id);
}
