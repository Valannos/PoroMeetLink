package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.BaseEntityDto;

import java.util.List;

/**
 * Interface {@link org.springframework.stereotype.Service} de base que chaque service implémente pour le CRUD.
 *
 * @param <T> Un type d'objet {@link BaseEntityDto}
 */
public interface BaseService<T extends BaseEntityDto, U> {

    /**
     * Récupère et renvoie toutes les entités sous forme de leur DTO respectives.
     * @return Une {@link List} d'objets de type T
     */
    List<T> getAll();

    /**
     * Returne un objet selon son identifiant si il existe.
     * @param id id de l'entitié
     * @return la DTO associée à cette entitié.
     */
    T getOne(U id) throws EntryNotFound, WrongOwnerException;

    /**
     * @param dto un objet de transfert non null
     * @return l'objet de transfert après sauvegarde de l'entité associée
     * @throws InvalidRoleException remonté si un rôle fournit est invalide
     * @throws InvalidFieldException remonté si l'un des champs obligatoire est invalide ou absent.
     */
    T saveOne(T dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound;

    /**
     *
     * @param dto l'objet DTO à mettre à jour
     * @return l'objet DTO mis à jour
     * @throws EntryNotFound l'objet n'a pas été trouvé
     * @throws InvalidFieldException un champs
     */
    T updateOne(T dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException;

    Boolean delete(U id) throws EntryNotFound, InvalidFieldException, InvalidRoleException, WrongOwnerException;

    /**
     * Méthode chargée de vérifier les champs du DTO (avant un save par example).
     * Cette méthode est à implémenter avec tous les critères de filtrages nécessaires selon l'entité.
     *
     * @param dto dto à analyser
     */
    void validator(T dto) throws NoSuchFieldException;

}
