package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.Security.tokens.JwtToken;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = UrlConstants.API_URL +  EntityUtils.UTILISATEUR)
public class UtilisateurController implements BaseController<UtilisateurDto> {


    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public List<UtilisateurDto> get() {
        return utilisateurService.getAll();
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto item) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException {

             item = utilisateurService.saveOne(item);

        return ResponseEntity.ok().body(item);
    }

    @Override
    public ResponseEntity<UtilisateurDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(utilisateurService.getOne(id));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) throws InvalidRoleException, EntryNotFound, InvalidFieldException {
        return ResponseEntity.ok(utilisateurService.delete(id));
    }

    @Override
    public ResponseEntity<UtilisateurDto> update(@RequestBody UtilisateurDto item) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(utilisateurService.updateOne(item));
    }

    @RequestMapping(path = UrlConstants.REFRESH_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<JwtToken> refreshToken(@RequestBody String token) throws WrongOwnerException {
        return ResponseEntity.ok(utilisateurService.refreshToken(token));
    }

    /**
     * Retourne un object {@link UtilisateurDto} selon le token renvoyé.
     * @return un object utilisateur associé à l'utilisateur courant.
     */
    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public ResponseEntity<UtilisateurDto> getCurrentUtilisateur() {
        return ResponseEntity.ok(utilisateurService.getAuthenticatedUtilisateurDto());
    }

    /**
     * Effectue une mise à jour d'un {@link fr.imie.poromeetlink.domain.entities.Utilisateur} dont l'id
     * est passé en paramètre en suspendant son compte
     * @param id id utilisateur
     * @return true si suspension ok
     */
    @RequestMapping(path = "/suspend/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UtilisateurDto> suspendUserById(@PathVariable Long id) throws EntryNotFound {

        return ResponseEntity.ok().body(utilisateurService.suspendre(id));
    }
}
