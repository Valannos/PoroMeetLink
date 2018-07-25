package fr.imie.poromeetlink.domain.entities;

import fr.imie.poromeetlink.outils.enumerations.TypeActeurEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * {@link Entity} de gestion des acteurs d'une annonce autre que l'auteur
 * Un acteur représente une fonction exercée par un {@link Employe} dans une {@link Annonce}
 *
 */
@Entity
@Table(name = "acteur")
public class Acteur extends ClassicEntity {

    @Column
    private Boolean actif;

    /**
     * Annonce à laquel participe l'acteur
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_annonce", nullable = false)
    private Annonce annonce;

    /**
     * Employe lié à cet acteur
     */
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employe employe;

    /**
     * Type d'acteur
     * @see TypeActeurEnum
     */
    @Enumerated(EnumType.STRING)
    private TypeActeurEnum typeActeur;

    /**
     * Utilisé uniquement par les {@link TypeActeurEnum#CONTROLEUR} pour valider ou non l'annonce
     */
    @Column(name = "valide_annonce", nullable = false)
    private Boolean valideAnnonce;

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Employe getActeur() {
        return employe;
    }

    public void setActeur(Employe acteur) {
        this.employe = acteur;
    }

    public TypeActeurEnum getTypeActeur() {
        return typeActeur;
    }

    public void setTypeActeur(TypeActeurEnum typeActeur) {
        this.typeActeur = typeActeur;
    }

    public Boolean getValideAnnonce() {
        return valideAnnonce;
    }

    public void setValideAnnonce(Boolean valideAnnonce) {
        this.valideAnnonce = valideAnnonce;
    }
}
