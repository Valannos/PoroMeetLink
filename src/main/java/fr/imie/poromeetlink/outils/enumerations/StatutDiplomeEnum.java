package fr.imie.poromeetlink.outils.enumerations;

public enum StatutDiplomeEnum {

    NON_OBTENU(0), EN_COURS(1), OBTENU(2);


    StatutDiplomeEnum(int i) {
    }

    private int value;

    public int getValue() {
        return value;
    }

}
