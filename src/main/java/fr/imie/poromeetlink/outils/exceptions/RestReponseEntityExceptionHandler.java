package fr.imie.poromeetlink.outils.exceptions;

import fr.imie.poromeetlink.outils.errors.BasicErrorEntity;
import fr.imie.poromeetlink.outils.errors.ErrorFieldsEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de gestion des erreur qui paramètre automatiquement la réponse depuis un controller.
 */
@ControllerAdvice
public class RestReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler pour {@link InvalidRoleException}
     *
     * @param ex l'exception levée
     * @return ResponseEntity avec un code 403
     */
    @ExceptionHandler(value = {InvalidRoleException.class})
    protected ResponseEntity<BasicErrorEntity> handleInvalidRole(InvalidRoleException ex) {

        return ResponseEntity.badRequest().body(new BasicErrorEntity(HttpStatus.FORBIDDEN, ex.getMessage()));
    }

    /**
     * Handler pour {@link EntryNotFound}
     * @param e l'exception levée
     * @return ResponseEntity avec un code 404
     */
    @ExceptionHandler(value = {EntryNotFound.class})
    protected ResponseEntity<BasicErrorEntity> handleNotFound(EntryNotFound e) {

        BasicErrorEntity errorEntity = new BasicErrorEntity(HttpStatus.NOT_FOUND, e.getMessage());

        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);

    }

    /**
     * Handler pour {@link InvalidFieldException}
     *
     * @param e l'exception levée
     * @return ResponseEntity avec un code 400 conteant un objet {@link ErrorFieldsEntity} avec le ou les champs qui ont posé problème.
     */
    @ExceptionHandler(value = {InvalidFieldException.class})
    protected ResponseEntity<ErrorFieldsEntity> handleInvalidField(InvalidFieldException e) {

        ErrorFieldsEntity errorEntity = new ErrorFieldsEntity(HttpStatus.BAD_REQUEST, e.getMessage(), e.getInvalidFields());

        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);

    }

    /**
     * Handler pour {@link NullDataTransfertObject}
     *
     * @param e l'exception levée
     * @return Un objet {@link ResponseEntity} contenant un objet {@link ErrorFieldsEntity}
     */
    @ExceptionHandler(value = {NullDataTransfertObject.class})
    protected ResponseEntity<ErrorFieldsEntity> handleNullDTO(NullDataTransfertObject e) {

        List<String> list = new ArrayList<>();

        list.add(e.getaClass().getSimpleName());

        ErrorFieldsEntity errorEntity = new ErrorFieldsEntity(HttpStatus.BAD_REQUEST, e.getMessage(), list);

        return ResponseEntity.badRequest().body(errorEntity);
    }

    /**
     * Handler pour {@link InsuffisantRightsException}
     *
     * @param e l'exception levée
     * @return ResponseEntity avec un code 403 pour cause de droits insuffisants avec un objet {@link BasicErrorEntity}
     */
    @ExceptionHandler(value = {InsuffisantRightsException.class})
    protected ResponseEntity<BasicErrorEntity> handleExpiredJwtException(InsuffisantRightsException e) {

        BasicErrorEntity errorEntity = new BasicErrorEntity(HttpStatus.FORBIDDEN, e.getMessage());

        return new ResponseEntity<>(errorEntity, HttpStatus.FORBIDDEN);

    }

    /**
     * Handler pour {@link DuplicateEntryException}
     *
     * @param e l'exception levée
     * @return ResponseEntity avec un code 403 pour cause de droits insuffisants avec un objet {@link BasicErrorEntity}
     */
    @ExceptionHandler(value = {DuplicateEntryException.class})
    protected ResponseEntity<BasicErrorEntity> handleDuplicateEntryException(DuplicateEntryException e) {

        BasicErrorEntity errorEntity = new BasicErrorEntity(HttpStatus.BAD_REQUEST, e.getMessage());

        return new ResponseEntity<>(errorEntity, errorEntity.getStatus());

    }




}
