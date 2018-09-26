package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.Security.tokens.JwtToken;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
import fr.imie.poromeetlink.service.services.UtilisateurService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = UrlConstants.API_URL +  EntityUtils.UTILISATEUR)
public class UtilisateurController extends BaseController<UtilisateurDto, UtilisateurService> {

    @Override
    public ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto item) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException {

             item = service.saveOne(item);

        return ResponseEntity.ok().body(item);
    }

    @Override
    public ResponseEntity<UtilisateurDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws InvalidRoleException, EntryNotFound, InvalidFieldException {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<UtilisateurDto> update(@RequestBody UtilisateurDto item) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }

    @RequestMapping(path = UrlConstants.REFRESH_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<JwtToken> refreshToken(@RequestBody String token) throws WrongOwnerException {
        return ResponseEntity.ok(service.refreshToken(token));
    }

    /**
     * Retourne un object {@link UtilisateurDto} selon le token renvoyé.
     * @return un object utilisateur associé à l'utilisateur courant.
     */
    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public ResponseEntity<UtilisateurDto> getCurrentUtilisateur() {
        return ResponseEntity.ok(service.getAuthenticatedUtilisateurDto());
    }

    /**
     * Effectue une mise à jour d'un {@link fr.imie.poromeetlink.domain.entities.Utilisateur} dont l'id
     * est passé en paramètre en suspendant son compte
     * @param id id utilisateur
     * @return true si suspension ok
     */
    @RequestMapping(path = "/suspend/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UtilisateurDto> suspendUserById(@PathVariable Long id) throws EntryNotFound {

        return ResponseEntity.ok().body(service.suspendre(id));
    }

	@Override
	List<UtilisateurDto> get() {
		return service.getAll();
	}
}
