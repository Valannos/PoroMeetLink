package fr.imie.poromeetlink.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * {@link Entity} pour les compétences
 */
@Entity
@Table(name = "competence")
public class Competence extends ClassicEntity implements Serializable {

    /**
     * serial
     */
    private static final long serialVersionUID = 4145608095589408771L;

    @NotNull
    @Column(name = "intitule", unique = true, nullable = false)
    private String intitule;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_secteur", nullable = false)
    private Secteur secteur;


    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }
}
