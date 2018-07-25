package fr.imie.poromeetlink.service.dto;

/**
 * CLasse DTO associée à {@link fr.imie.poromeetlink.domain.entities.Secteur}
 */
public class SecteurDto extends ClassicDto {

    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
