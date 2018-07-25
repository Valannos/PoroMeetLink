package fr.imie.poromeetlink.domain.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;

/**
 * {@link Entity} pour gérer les infos concernant une {@link Competence} déclarée par un {@link Candidat}.
 */
@Entity
@Table(name = "competence_candidat")
public class CompetenceCandidat extends BaseEntity implements Serializable {

    @EmbeddedId
    private CompetenceCandidatId id;

    private static final long serialVersionUID = 4107697663188485820L;

    @MapsId("idCompetence")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_competence")
    private Competence competence;

    @MapsId("idCandidat")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;

    @Column
    @Range(min = 1, max = 10)
    private Short niveau;

    public Short getNiveau() {
        return niveau;
    }

    public void setNiveau(Short niveau) {
        this.niveau = niveau;
    }

    public CompetenceCandidatId getId() {
        return id;
    }

    public void setId(CompetenceCandidatId id) {
        this.id = id;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
}
