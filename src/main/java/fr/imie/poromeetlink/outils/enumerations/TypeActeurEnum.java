package fr.imie.poromeetlink.outils.enumerations;

/**
 * {@link java.util.Enumeration} pour les type d'acteurs
 */
public enum TypeActeurEnum {

    RAPPORTEUR("rapporteur"), CONTROLEUR("controleur");

    TypeActeurEnum(String value) {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
