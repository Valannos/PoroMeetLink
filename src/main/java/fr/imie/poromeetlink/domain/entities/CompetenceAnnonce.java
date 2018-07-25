package fr.imie.poromeetlink.domain.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * {@link Entity} pour gérer les infos concernant une {@link Competence} requise pour une {@link Annonce}.
 */
@Entity
@Table(name = "competence_annonce")
public class CompetenceAnnonce extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6351761100593415111L;

    @EmbeddedId
    private CompetenceAnnonceId id;

    @MapsId("idAnnonce")
    @ManyToOne
    @JoinColumn(name = "id_annonce")
    private Annonce annonce;

    @MapsId("idCompetence")
    @ManyToOne
    @JoinColumn(name = "id_competence")
    private Competence competence;

    @Column(name = "niveau_requis")
    @Range(min = 1, max = 10)
    private Short niveauRequis;

    public CompetenceAnnonce(Annonce annonce, Competence competence, @Size(min = 1, max = 10) Short niveauRequis) {
        this.annonce = annonce;
        this.competence = competence;
        this.niveauRequis = niveauRequis;
    }

    public CompetenceAnnonce() {
    }

    public CompetenceAnnonceId getId() {
        return id;
    }

    public void setId(CompetenceAnnonceId id) {
        this.id = id;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Short getNiveauRequis() {
        return niveauRequis;
    }

    public void setNiveauRequis(Short niveauRequis) {
        this.niveauRequis = niveauRequis;
    }
}
