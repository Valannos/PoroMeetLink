package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.AnnonceDto;
import fr.imie.poromeetlink.service.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.ANNONCE)
public class AnnonceController implements BaseController<AnnonceDto> {

    @Autowired
    private AnnonceService service;

    @Override
    public List<AnnonceDto> get() {
        return service.getAll();
    }

    @Override
    public ResponseEntity<AnnonceDto> save(@RequestBody AnnonceDto item) throws InsuffisantRightsException, InvalidRoleException, InvalidFieldException, NoSuchFieldException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(service.saveOne(item));
    }

    @Override
    public ResponseEntity<AnnonceDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<AnnonceDto> update(@RequestBody AnnonceDto item) throws InvalidFieldException, EntryNotFound, WrongOwnerException, NoSuchFieldException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }

    @RequestMapping(path = "/by-entreprise/{id}", method = RequestMethod.GET)
    public List<AnnonceDto> getAllByAuteurId(@PathVariable Long id) throws WrongOwnerException, EntryNotFound {
        return service.getAllByEntreprise(id);
    }

    @RequestMapping(path = "/by-entreprise/valide", method = RequestMethod.GET)
    public @ResponseBody List<AnnonceDto> getAllValide() throws InterruptedException {
        Thread.sleep(100);
        return service.getAllValidated();
    }

    @RequestMapping(path = "/toggle/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<AnnonceDto> toggleValidity(@PathVariable Long id) throws EntryNotFound {
      return ResponseEntity.ok().body(this.service.toggleAnnonce(id));
    }
}
