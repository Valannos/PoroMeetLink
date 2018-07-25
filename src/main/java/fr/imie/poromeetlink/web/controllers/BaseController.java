package fr.imie.poromeetlink.web.controllers;


import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.BaseEntityDto;
import fr.imie.poromeetlink.service.dto.ClassicDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interface pour les controllers
 * NOTE : à l'heure actuelle, les annorations @{@link RequestBody} et @{@link PathVariable} sont ignorées dans l'implémentation ; il faut les ajouter à la main...
 * @param <T>
 */
public interface BaseController<T extends BaseEntityDto> {

    /**
     * Récupération de toutes champs associés à l'entité T
     * @return Une {@link List} d'object {@link BaseEntityDto}
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody List<T> get();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<T> save(T item) throws InsuffisantRightsException, InvalidRoleException, InvalidFieldException, NoSuchFieldException, InvalidLoginException, WrongOwnerException, DuplicateEntryException, EntryNotFound;

    @RequestMapping(method = RequestMethod.GET, path = UrlConstants.ID_URL)
    @ResponseBody ResponseEntity<T> get(Long id) throws EntryNotFound, WrongOwnerException;

    @RequestMapping(method = RequestMethod.DELETE, path = UrlConstants.ID_URL)
    ResponseEntity delete(Long id) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    ResponseEntity<T> update(T item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException;

}
