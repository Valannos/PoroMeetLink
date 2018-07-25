package fr.imie.poromeetlink.outils.enumerations;

public enum SexeEnum {

    MASCULIN("M"), FEMININ("F");


    SexeEnum(String genre) {
    }

    private String genre;

    public String getGenre() {
        return genre;
    }
}
