package fr.imie.poromeetlink.outils.exceptions;

import java.util.List;

public class InvalidFieldException extends Exception {

    private static final long serialVersionUID = 5657936725709311249L;

    private List<String> invalidFields;

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public InvalidFieldException(String message, List<String> invalidFields) {
        super(message);
        this.invalidFields = invalidFields;
    }


}
