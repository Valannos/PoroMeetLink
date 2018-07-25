package fr.imie.poromeetlink.outils.exceptions;

public class InvalidLoginException extends Exception {

    private static final long serialVersionUID = 5817962942075442309L;

    public InvalidLoginException(String message) {
        super(message);
    }
}
