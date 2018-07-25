package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.DiplomeDto;
import fr.imie.poromeetlink.service.services.DiplomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.DIPLOME)
public class DiplomeController implements BaseController<DiplomeDto> {

    @Autowired
    DiplomeService service;


    @Override
    public List<DiplomeDto> get() {
        return service.getAll();
    }

    @Override
    public ResponseEntity<DiplomeDto> save(@RequestBody DiplomeDto item) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(service.saveOne(item));
    }

    @Override
    public ResponseEntity<DiplomeDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<DiplomeDto> update(@RequestBody DiplomeDto item) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/candidat" + UrlConstants.ID_URL)
    public List<DiplomeDto> getAllByCandidat(@PathVariable Long id) {
        return this.service.getAllByCandidatId(id);
    }
}
