package fr.imie.poromeetlink.outils.errors;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Classe fille de {@link AbstractError} permettant de renvoyer une liste de champs invalides.
 */
public class ErrorFieldsEntity extends AbstractError {


    public ErrorFieldsEntity(HttpStatus status, String message, List<String> invalidFields) {
        super(status, message);
        this.invalidFields = invalidFields;

    }

    /**
     * {@link List} des champs invalidés par la couche service
     */
    private List<String> invalidFields;

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(List<String> invalidFields) {
        this.invalidFields = invalidFields;
    }



}
