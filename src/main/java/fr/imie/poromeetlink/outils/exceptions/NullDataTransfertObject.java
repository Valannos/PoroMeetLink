package fr.imie.poromeetlink.outils.exceptions;

/**
 * {@link Exception} levée lorsque le champs d'un DTO est null
 */
public class NullDataTransfertObject extends NullPointerException {
    private static final long serialVersionUID = -8946442883242693068L;

    private Class aClass;

    public NullDataTransfertObject(String s, Class tClass) {
        super(s);
        this.aClass = tClass;
    }

    public NullDataTransfertObject(String s) {
        super(s);
    }

    public Class getaClass() {
        return aClass;
    }
}
