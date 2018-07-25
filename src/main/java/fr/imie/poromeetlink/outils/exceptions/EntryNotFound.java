package fr.imie.poromeetlink.outils.exceptions;

/**
 * {@link Exception} levée lorsqu'une entrée n'a pu être trouvé en base pour un identifiant donné.
 */
public class EntryNotFound extends Exception {

    private static final long serialVersionUID = 7223821795999600277L;
    private String field;

    public EntryNotFound(String message) {
        super(message);
    }

    public EntryNotFound(String message, String field) {
        super(message);
        this.field = field;

    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
