package fr.imie.poromeetlink.outils.enumerations;

/**
 * {@link Enum} pour gérer les rôles
 * @deprecated
 */
public enum RoleEnum {

    ADMINISTRATEUR_SITE("ADMINISTRATEUR_SITE"),
    ADMINISTRATEUR_COMPTE("ADMINISTRATEUR_COMPTE"),
    GERANT("GERANT"),
    DEMARCHEUR("DEMARCHEUR"),
    CANDIDAT("CANDIDAT"),
    SUPER_CONTROLEUR("SUPER_CONTROLEUR"),
    SUPER_RAPPORTEUR("SUPER_RAPPORTEUR"),
    RECRUTEUR("RECRUTEUR");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
