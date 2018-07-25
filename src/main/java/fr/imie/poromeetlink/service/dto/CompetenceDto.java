package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotNull;

public class CompetenceDto extends ClassicDto {

    @NotNull
    private String intitule;

    @NotNull
    private SecteurDto secteur;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public SecteurDto getSecteur() {
        return secteur;
    }

    public void setSecteur(SecteurDto secteur) {
        this.secteur = secteur;
    }
}
