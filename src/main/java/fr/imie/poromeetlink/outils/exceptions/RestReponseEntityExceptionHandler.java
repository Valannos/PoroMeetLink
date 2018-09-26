package fr.imie.poromeetlink.outils.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.imie.poromeetlink.outils.errors.BasicErrorEntity;
import fr.imie.poromeetlink.outils.errors.ErrorFieldsEntity;

/**
 * Classe de gestion des erreur qui paramètre automatiquement la réponse depuis un controller.
 * 
 * @see https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
 */
@ControllerAdvice
public class RestReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestReponseEntityExceptionHandler.class);


    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
    	List<String> errors = new ArrayList<>();
    	ex.getBindingResult().getFieldErrors().forEach(err -> {
    		LOGGER.debug(err.getDefaultMessage());
    		errors.add(err.getObjectName() + "."+ err.getField() + ":" + err.getDefaultMessage());
    	});

    	final ErrorFieldsEntity errorFieldsEntity = new ErrorFieldsEntity(status, ex.getLocalizedMessage(), errors);
    	return ResponseEntity.status(status).body(errorFieldsEntity);
	}

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
     * @deprecated en cours de remplacement.
     */
    @Deprecated
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
