package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.EntrepriseDto;
import fr.imie.poromeetlink.service.services.EntrepriseService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.ENTREPRISE)
public class EntrepriseController extends BaseController<EntrepriseDto, EntrepriseService> {

    @Override
    public ResponseEntity<EntrepriseDto> save(@RequestBody EntrepriseDto item) throws InvalidFieldException, InvalidRoleException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(service.saveOne(item));
    }

    @Override
    public ResponseEntity<EntrepriseDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws InvalidRoleException, EntryNotFound, InvalidFieldException, WrongOwnerException {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<EntrepriseDto> update(@RequestBody EntrepriseDto item) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }

    /**
     * Retourne l'entreprise associée à l'utilisateur connectée si elle existe.
     * @return un objet {@link EntrepriseDto}
     * @throws EntryNotFound
     */
    @RequestMapping(path = "getByCurrentUtilisateur")
    public ResponseEntity<EntrepriseDto> getByUtilisateur() throws EntryNotFound {
        return ResponseEntity.ok(this.service.getEntrepriseByUtilisateur());
    }

	@Override
	List<EntrepriseDto> get() {
		return service.getAll();
	}
}
