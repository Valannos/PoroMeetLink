package fr.imie.poromeetlink.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * {@link Entity} pour gérer les propositions de {@link Candidature} émanant d'un {@link Candidat} ou d'un {@link Acteur}
 */
@Entity
@Table(name = "proposition_candidature")
public class PropositionCandidature extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7009442524449247181L;

    @EmbeddedId
    private PropositionCandidatureId id;

    @MapsId("idCandidat")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;

    @MapsId("idAnnonce")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_annonce")
    private Annonce annonce;

    @ManyToOne
    private Candidature candidature;

    @NotNull
    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;

    @Column(name = "est_acceptee")
    private Boolean acceptee;

    @Column
    private String accroche;

    @NotNull
    @Column(name = "is_auteur_candidat")
    private Boolean auteurCandidat;

    public PropositionCandidature(PropositionCandidatureId id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Candidature getCandidature() {
        return candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getAcceptee() {
        return acceptee;
    }

    public void setAcceptee(Boolean acceptee) {
        this.acceptee = acceptee;
    }

    public String getAccroche() {
        return accroche;
    }

    public void setAccroche(String accroche) {
        this.accroche = accroche;
    }

    public PropositionCandidatureId getId() {
        return id;
    }

    public void setId(PropositionCandidatureId id) {
        this.id = id;
    }

    public Boolean getAuteurCandidat() {
        return auteurCandidat;
    }

    public void setAuteurCandidat(Boolean auteurCandidat) {
        this.auteurCandidat = auteurCandidat;
    }

    public PropositionCandidature() {
    }
}
