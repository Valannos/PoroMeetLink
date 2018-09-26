package fr.imie.poromeetlink.web.controllers;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.DuplicateEntryException;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InsuffisantRightsException;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidLoginException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.outils.exceptions.WrongOwnerException;
import fr.imie.poromeetlink.service.dto.BaseEntityDto;
import fr.imie.poromeetlink.service.services.BaseService;
import fr.imie.poromeetlink.service.services.MessageProvider;

/**
 * Classe abstraite pour les controllers
 * NOTE : à l'heure actuelle, les annorations @{@link RequestBody} et @{@link PathVariable} sont ignorées dans l'implémentation ; il faut les ajouter à la main...
 * @param <T> DTO gérée par le webservice
 * @param <U> service injecté dans le webservice
 */
public abstract class BaseController<T extends BaseEntityDto, U extends BaseService> {

    @Autowired
    U service;
    
    @Autowired
    protected MessageProvider messageProvider;

    /**
     * Récupération de toutes champs associés à l'entité T
     * @return Une {@link List} d'object {@link BaseEntityDto}
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody 
    abstract List<T> get();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    abstract ResponseEntity<T> save(T item) throws InsuffisantRightsException, InvalidRoleException, InvalidFieldException, NoSuchFieldException, InvalidLoginException, WrongOwnerException, DuplicateEntryException, EntryNotFound;

    @RequestMapping(method = RequestMethod.GET, path = UrlConstants.ID_URL)
    @ResponseBody abstract ResponseEntity<T> get(Long id) throws EntryNotFound, WrongOwnerException;

    @RequestMapping(method = RequestMethod.DELETE, path = UrlConstants.ID_URL)
    abstract ResponseEntity<Boolean> delete(Long id) throws EntryNotFound, InvalidRoleException, InvalidFieldException, WrongOwnerException;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    abstract ResponseEntity<T>  update(T item) throws NoSuchFieldException, EntryNotFound, InvalidFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException;
    
    protected void checkIfNull(T item) {
        if (Objects.isNull(item)) {
         Class<? extends BaseEntityDto> clazz = item.getClass();
       	 throw new NullDataTransfertObject(messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", clazz));
       }
    }

}
