package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.domain.entities.CompetenceCandidat;
import fr.imie.poromeetlink.domain.entities.CompetenceCandidatId;
import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.CompetenceCandidatDto;
import fr.imie.poromeetlink.service.services.CompetenceCandidatService;
import fr.imie.poromeetlink.service.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.COMPETENCE_CANDIDAT)
public class CompetenceCandidatController {

    @Autowired
    CompetenceCandidatService service;

    @Autowired
    UtilisateurService utilisateurService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<CompetenceCandidatDto> get() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<CompetenceCandidatDto> save(@RequestBody CompetenceCandidatDto item) throws InsuffisantRightsException, InvalidRoleException, InvalidFieldException, NoSuchFieldException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(service.saveOne(item));
    }

    @RequestMapping(method = RequestMethod.GET, path = UrlConstants.ID_CANDIDAT_ID_COMPETENCE)
    public @ResponseBody ResponseEntity<CompetenceCandidatDto> get(@PathVariable Long idCandidat, @PathVariable Long idCompetence) throws EntryNotFound, WrongOwnerException {
        CompetenceCandidatId id = new CompetenceCandidatId(idCandidat, idCompetence);
        return ResponseEntity.ok(service.getOne(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = UrlConstants.ID_CANDIDAT_ID_COMPETENCE)
    public @ResponseBody ResponseEntity delete(@PathVariable Long idCandidat, @PathVariable Long idCompetence) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException {
        Utilisateur utilisateur = utilisateurService.getAuthenticatedUtilisateur();
        CompetenceCandidatId id = new CompetenceCandidatId(idCompetence, idCandidat);
        return ResponseEntity.ok(service.delete(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<CompetenceCandidatDto> update(@RequestBody CompetenceCandidatDto item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, WrongOwnerException, InterruptedException, InvalidRoleException, InsuffisantRightsException {
        Thread.sleep(1000);
        return ResponseEntity.ok(service.updateOne(item));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/candidat/{id}")
    public @ResponseBody List<CompetenceCandidatDto> getAllByCandidat(@PathVariable Long id) throws EntryNotFound {
        return service.getAllByCandidat(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/competence/exists/{id}")
    public @ResponseBody ResponseEntity<Boolean> checkExisteByCompetence(@PathVariable Long id) throws EntryNotFound {
        return ResponseEntity.ok(this.service.checkExisteByCompetence(id));
    }
}
