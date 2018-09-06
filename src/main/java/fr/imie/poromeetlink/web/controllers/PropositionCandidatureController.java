package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.domain.entities.PropositionCandidatureId;
import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.PropositionCandidatureDto;
import fr.imie.poromeetlink.service.services.PropositionCandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.PROPOSITION_CANDIDATURE)
public class PropositionCandidatureController {

    @Autowired
    PropositionCandidatureService service;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<PropositionCandidatureDto> get() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<PropositionCandidatureDto> save(@RequestBody PropositionCandidatureDto item) throws InsuffisantRightsException, InvalidRoleException, InvalidFieldException, NoSuchFieldException, InvalidLoginException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok().body(service.saveOne(item));
    }

    @RequestMapping(method = RequestMethod.GET, path = UrlConstants.ID_CANDIDAT_ID_ANNONCE)
    public @ResponseBody ResponseEntity<PropositionCandidatureDto> get(@PathVariable Long idAnnonce, @PathVariable Long idCandidat) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok().body(service.getOne(new PropositionCandidatureId(idCandidat, idAnnonce)));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = UrlConstants.ID_CANDIDAT_ID_ANNONCE)
    public @ResponseBody ResponseEntity<Boolean> delete(@PathVariable Long idAnnonce, @PathVariable Long idCandidat) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException {
        return ResponseEntity.ok().body(service.delete(new PropositionCandidatureId(idCandidat, idAnnonce)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<PropositionCandidatureDto> update(@RequestBody PropositionCandidatureDto item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, WrongOwnerException, InvalidRoleException {
        return null;
    }
}
