package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.domain.entities.CompetenceAnnonceId;
import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CompetenceAnnonceDto;
import fr.imie.poromeetlink.service.services.CompetenceAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.COMPETENCE_ANNONCE)
public class CompetenceAnnonceController {

    @Autowired
    CompetenceAnnonceService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CompetenceAnnonceDto> get() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CompetenceAnnonceDto> save(@RequestBody CompetenceAnnonceDto item) throws InsuffisantRightsException, InvalidRoleException, InvalidFieldException, NoSuchFieldException, InvalidLoginException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(service.saveOne(item));
    }


    @RequestMapping(method = RequestMethod.GET, path = UrlConstants.ID_ANNONCE_ID_COMPETENCE)
    public ResponseEntity<CompetenceAnnonceDto> get(@PathVariable Long idAnnonce, @PathVariable Long idCompetence) throws EntryNotFound, WrongOwnerException {
        CompetenceAnnonceId id = new CompetenceAnnonceId();
        id.setIdAnnonce(idAnnonce);
        id.setIdCompetence(idCompetence);
        return ResponseEntity.ok(service.getOne(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = UrlConstants.ID_ANNONCE_ID_COMPETENCE)
    public ResponseEntity delete(@PathVariable Long idAnnonce, @PathVariable Long idCompetence) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException {
        CompetenceAnnonceId id = new CompetenceAnnonceId();
        id.setIdAnnonce(idAnnonce);
        id.setIdCompetence(idCompetence);
        return ResponseEntity.ok(service.delete(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CompetenceAnnonceDto> update(@RequestBody CompetenceAnnonceDto item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }
}
