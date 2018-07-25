package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.COMPETENCE)
public class CompetenceController implements BaseController<CompetenceDto> {

    @Autowired
    CompetenceService service;

    @Override
    public List<CompetenceDto> get() {
        return service.getAll();
    }

    @Override
    public ResponseEntity<CompetenceDto> save(@RequestBody CompetenceDto item) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(service.saveOne(item));
    }

    @Override
    public ResponseEntity<CompetenceDto> get(@RequestBody Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) throws InvalidFieldException, EntryNotFound, InvalidRoleException, WrongOwnerException {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<CompetenceDto> update(@RequestBody CompetenceDto item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }

    @RequestMapping(path = "/bySecteur/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<CompetenceDto> getAllBySecteur(@PathVariable Long id) throws EntryNotFound {
        return this.service.getAllByService(id);
    }
}
