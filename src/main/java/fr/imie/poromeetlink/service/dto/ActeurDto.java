package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.outils.enumerations.TypeActeurEnum;

/**
 * DTO pour {@link fr.imie.poromeetlink.domain.entities.Acteur}
 */
public class ActeurDto extends ClassicDto {


    private Boolean actif;

    /**
     * Annonce à laquel participe l'acteur
     */

    private Long annonceId;

    /**
     * Employe lié à cet acteur
     */
    private Long acteurId;

    /**
     * Type d'acteur
     * @see TypeActeurEnum
     */
     private TypeActeurEnum typeActeur;

    /**
     * Utilisé uniquement par les {@link TypeActeurEnum#CONTROLEUR} pour valider ou non l'annonce
     */
    private Boolean valideAnnonce;

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Long getAnnonceId() {
        return annonceId;
    }

    public void setAnnonceId(Long annonceId) {
        this.annonceId = annonceId;
    }

    public Long getActeurId() {
        return acteurId;
    }

    public void setActeurId(Long acteurId) {
        this.acteurId = acteurId;
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
