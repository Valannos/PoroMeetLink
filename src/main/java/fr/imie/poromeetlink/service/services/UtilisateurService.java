package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.domain.entities.Role;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.Security.tokens.JwtToken;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Interface pour les services utilisateurs
 */
public interface UtilisateurService extends BaseService<UtilisateurDto, Long> {

    Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

    /**
     * Méthode qui contrôle le login de l'utilisateur au moment de l'enregistrement de ce dernier en vérifiant notamment
     * qu'il ne soit pas déjà utilisé.
     *
     * @param login le login à contrôler
     * @return true si valide false sinon
     */
    Boolean loginValidator(String login);

    /**
     * @param username recherche un {@link Utilisateur} et charge ses {@link fr.imie.poromeetlink.domain.entities.Role}
     * @return Un utilisateur
     */
    @Transactional
    Utilisateur findByLogin(String username);


    @Override
    UtilisateurDto saveOne(UtilisateurDto dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException;

    /**
     * Vérifie que le {@link Role} transmis est bien présent dans la base de donnée. Permets de contrôler que le {@link Role}
     * transmis respecte l'intégrité des données (pas de rôle non connu).
     * //TODO Rajouter la possibilité de contrôler que n'importe qui ne peut pas s'attribuer n'importe quel role.
     * @param role Le {@link Role} contrôlé
     * @return true si le contrôle est passé, false sinon.
     */
    Boolean checkRole(Role role) throws InvalidRoleException, InsuffisantRightsException;

    /**
     * Rafraichit le token passé en paramètre.
     * @param jwtToken le token à renouveler
     * @return le token renouvellé
     */
    JwtToken refreshToken(String jwtToken) throws WrongOwnerException;

    /**
     * Vérifie d'une part si le nom d'utilisateur correspond à un utilisateur connu et
     * si ce dernier est l'utilisateur connecté.
     * @param username le nom d'utilisateur
     * @return true si le username correspond à l'utilisateur connecté.
     */
    Boolean validateLoggedUser(String username);

    @PreAuthorize("isAuthenticated()")
    Utilisateur getAuthenticatedUtilisateur();

    @PreAuthorize("isAuthenticated()")
    @Transactional
    UtilisateurDto getAuthenticatedUtilisateurDto();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    @Transactional
    List<UtilisateurDto> getAll();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    Boolean delete(Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException;

    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    UtilisateurDto suspendre(Long id) throws EntryNotFound;

    @PreAuthorize("isAuthenticated()")
    Boolean exists(Long id);
}
