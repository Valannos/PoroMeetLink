package fr.imie.poromeetlink.outils.exceptions;

/**
 * Une {@link Exception} qui gère le manque de droit pour effectuer une action.
 */
public class InsuffisantRightsException extends Exception {

    /**
	 * serial
	 */
	private static final long serialVersionUID = 5417087310203564893L;
	/**
     * Le nom du droit minimum pour effectuer l'action.
     */
    private String roleName;

    public InsuffisantRightsException(String message, String roleName) {
        super(message);
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
