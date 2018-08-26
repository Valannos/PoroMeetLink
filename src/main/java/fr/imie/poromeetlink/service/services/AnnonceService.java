package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.outils.exceptions.WrongOwnerException;
import fr.imie.poromeetlink.service.dto.AnnonceDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * {@link org.springframework.stereotype.Service} for {@link fr.imie.poromeetlink.domain.entities.Annonce}
 */
public interface AnnonceService extends BaseService<AnnonceDto, Long> {

    /**
     *
     * @param id identifiant de l'{@link fr.imie.poromeetlink.domain.entities.Employe}
     * @return Retourne toutes les annonces d'un employé selons son identifiant.
     * @throws EntryNotFound levée si l'employé n'est pas trouvé.
     * @throws WrongOwnerException levée si l'utilisateur connecté n'est pas l'employé.
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_COMPTE') or hasRole('ROLE_GERANT') or hasRole('ROLE_RECRUTEUR')")
    List<AnnonceDto> getAllByEntreprise(Long id) throws EntryNotFound, WrongOwnerException;

    /**
     *
     * @return toutes les annonces validées
     */
    @PreAuthorize("hasRole('ROLE_CANDIDAT')")
    List<AnnonceDto> getAllValidated();

    /**
     * Change le statut de validation d'une {@link fr.imie.poromeetlink.domain.entities.Annonce}
     * @param id identifiant de l'annonce
     * @return objet {@link fr.imie.poromeetlink.domain.entities.Annonce} mis à jour
     * @throws EntryNotFound levée si l'annonce n'a pas été trouvée
     */
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_COMPTE') or hasRole('ROLE_GERANT') or hasRole('ROLE_RECRUTEUR')")
    AnnonceDto toggleAnnonce(Long id) throws EntryNotFound;

    AnnonceDto saveOrUpdate(AnnonceDto dto) throws InvalidRoleException;

}
