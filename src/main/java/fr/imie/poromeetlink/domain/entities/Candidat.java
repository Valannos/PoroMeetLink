package fr.imie.poromeetlink.domain.entities;

import fr.imie.poromeetlink.outils.enumerations.SexeEnum;
import fr.imie.poromeetlink.outils.enumerations.PaysEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Entity} pour gérer les candidats
 */
@Entity
@Table(name = "candidat")
public class Candidat extends ClassicEntity implements Serializable {

    /**
     * serial
     */
    private static final long serialVersionUID = 1131290254637824202L;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contact> contacts = new HashSet<>();

    @Column
    private String nom;

    @Column
    private String prenom;

    @Enumerated(EnumType.STRING)
    private SexeEnum sexe;

    @Column
    private String ville;

    @Column(name = "url_avatar")
    private String urlAvatar;

    @Enumerated(EnumType.STRING)
    private PaysEnum pays;

    @Column(name = "date_naissance")
    private ZonedDateTime dateNaissance;

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    //@JoinTable(joinColumns = @JoinColumn(name = "id_candidat"), inverseJoinColumns = @JoinColumn(name = "id_diplome"))
    private Set<Diplome> diplomes = new HashSet<>();

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    private Set<CompetenceCandidat> competenceCandidats = new HashSet<>();

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    private Set<PropositionCandidature> propositionCandidatures = new HashSet<>();

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    private Set<SavoirEtre> savoirEtres = new HashSet<>();


    public SexeEnum getSexe() {
        return sexe;
    }

    public void setSexe(SexeEnum sexe) {
        this.sexe = sexe;
    }

    public Set<CompetenceCandidat> getCompetenceCandidats() {
        return competenceCandidats;
    }

    public void setCompetenceCandidats(Set<CompetenceCandidat> competenceCandidats) {
        this.competenceCandidats = competenceCandidats;
    }

    public Set<PropositionCandidature> getPropositionCandidatures() {
        return propositionCandidatures;
    }

    public void setPropositionCandidatures(Set<PropositionCandidature> propositionCandidatures) {
        this.propositionCandidatures = propositionCandidatures;
    }

    public Set<SavoirEtre> getSavoirEtres() {
        return savoirEtres;
    }

    public void setSavoirEtres(Set<SavoirEtre> savoirEtres) {
        this.savoirEtres = savoirEtres;
    }

    public Set<Diplome> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(Set<Diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public PaysEnum getPays() {
        return pays;
    }

    public void setPays(PaysEnum pays) {
        this.pays = pays;
    }

    public ZonedDateTime getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(ZonedDateTime dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
