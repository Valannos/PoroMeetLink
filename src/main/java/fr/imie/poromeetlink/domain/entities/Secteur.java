package fr.imie.poromeetlink.domain.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * {@link Entity} qui gère les secteurs d'activité
 */
@Entity
@Table(name = "secteur")
public class Secteur extends ClassicEntity implements Serializable {

    /**
     * serial
     */
    private static final long serialVersionUID = 5210091303398912527L;

    @NotNull
    @Column(name = "libelle", unique = true)
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
