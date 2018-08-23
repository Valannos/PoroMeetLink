package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InsuffisantRightsException;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.service.services.SecteurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.SECTEUR)
public class SecteurController extends BaseController<SecteurDto, SecteurService>{

    private static final Logger LOGGER = LoggerFactory.getLogger(SecteurController.class);

    @Override
    public ResponseEntity<SecteurDto> save(@RequestBody SecteurDto item) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException {
        return ResponseEntity.ok(service.saveOne(item));
    }

    @Override
    public ResponseEntity<SecteurDto> get(@PathVariable Long id) throws EntryNotFound {
        LOGGER.info("GET, Id: " + id);
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) throws InvalidRoleException, EntryNotFound, InvalidFieldException {
        LOGGER.info("DELETE, Id: " + id);
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<SecteurDto> update(@RequestBody SecteurDto item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, InsuffisantRightsException {

        return ResponseEntity.ok(service.updateOne(item));
    }
}
