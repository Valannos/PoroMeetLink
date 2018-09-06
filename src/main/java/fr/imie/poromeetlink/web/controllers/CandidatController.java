package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CandidatDto;
import fr.imie.poromeetlink.service.services.CandidatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link RestController} pour {@link fr.imie.poromeetlink.domain.entities.Candidat}
 */
@RestController
@RequestMapping(path = "/api/" + EntityUtils.CANDIDAT)
public class CandidatController extends BaseController<CandidatDto, CandidatService> {

    @Override
    public ResponseEntity<CandidatDto> save(@RequestBody CandidatDto item) throws InsuffisantRightsException, InvalidRoleException, InvalidFieldException, NoSuchFieldException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok().body(service.saveOne(item));
    }

    @Override
    public ResponseEntity<CandidatDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException {
        return ResponseEntity.ok().body(service.delete(id));
    }

    @Override
    public ResponseEntity<CandidatDto> update(@RequestBody CandidatDto item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }

}
