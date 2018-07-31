package fr.imie.poromeetlink.domain.entities;

import fr.imie.poromeetlink.outils.enumerations.TypeContrat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Entity} pour les annonces
 */
@Entity
@Table(name = "annonce")
public class Annonce extends ClassicEntity implements Serializable {

    private static final long serialVersionUID = 6589073822847526288L;

    @NotNull
    @Column(name = "intitule", nullable = false)
    private String intitule;

    @NotNull
    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;

    @Column(name = "date_debut")
    private ZonedDateTime dateDebut;

    @Column(name = "niveau_experience")
    private String niveauExperience;

    @Column(name = "date_fin")
    private ZonedDateTime dateFin;

    @Column(name = "is_valid", nullable = false)
    private Boolean valid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "annonce", cascade = CascadeType.ALL)
    private Set<Acteur> acteurs = new HashSet<>();

    @NotNull
    @Column
    private String description;

    @OneToMany(mappedBy = "annonce", cascade = CascadeType.ALL)
    private Set<CompetenceAnnonce> competenceAnnonce = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private TypeContrat typeContrat;

    @NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    private Employe employe;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "annonce")
    private Set<PropositionCandidature> propositionCandidatures = new HashSet<>();

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ZonedDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CompetenceAnnonce> getCompetenceAnnonce() {
        return competenceAnnonce;
    }

    public void setCompetenceAnnonce(Set<CompetenceAnnonce> competenceAnnonce) {
        this.competenceAnnonce = competenceAnnonce;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Set<PropositionCandidature> getPropositionCandidatures() {
        return propositionCandidatures;
    }

    public void setPropositionCandidatures(Set<PropositionCandidature> propositionCandidatures) {
        this.propositionCandidatures = propositionCandidatures;
    }

    public String getNiveauExperience() {
        return niveauExperience;
    }

    public void setNiveauExperience(String niveauExperience) {
        this.niveauExperience = niveauExperience;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
