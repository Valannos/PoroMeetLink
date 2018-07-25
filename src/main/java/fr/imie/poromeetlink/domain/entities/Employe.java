package fr.imie.poromeetlink.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Entity} pour gérer les employés
 */
@Entity
@Table(name = "employe")
public class Employe extends ClassicEntity implements Serializable {

    private static final long serialVersionUID = 766848058621067259L;

    /**
     * Nom du poste occupe
     */
    @Column(name = "libelle_poste")
    private String libellePoste;

    /**
     * nom de l'employé
     */
    @Column(name = "nom")
    private String nom;

    /**
     * prénom de l'employé
     */
    @Column(name = "prenom")
    private String prenom;

    /**
     * {@link Utilisateur} associé à cet employe
     */
    @NotNull
    @OneToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    /**
     * Contacts parmis les candidats
     */
    @OneToMany(fetch =FetchType.LAZY, mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contact> contacts = new HashSet<>();

    /**
     * {@link Entreprise} à laquelle appartient l'employe
     */
    @NotNull
    @ManyToOne
    @JoinTable(name = "employes_entreprise",
            joinColumns = @JoinColumn(name = "id_employe"),
            inverseJoinColumns = @JoinColumn(name = "id_entreprise"))
    private Entreprise entreprise;

    /**
     * liste d'acteurs
     */
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Acteur> acteurs = new HashSet<>();

    /**
     * {@link Annonce} dont l'employe est l'auteur
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employe")
    private Set<Annonce> annonces = new HashSet<>();

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public String getLibellePoste() {
        return libellePoste;
    }

    public void setLibellePoste(String libellePoste) {
        this.libellePoste = libellePoste;
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

    public Set<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Set<Annonce> annonces) {
        this.annonces = annonces;
    }
}
