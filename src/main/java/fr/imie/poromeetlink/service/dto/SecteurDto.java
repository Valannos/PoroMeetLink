package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotEmpty;

/**
 * DTO for {@link fr.imie.poromeetlink.domain.entities.Secteur}
 */
public class SecteurDto extends ClassicDto {

    /**
     * libelle
     */
	@NotEmpty(message = "{NotEmpty.secteur.libelle}")
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
