package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for {@link fr.imie.poromeetlink.domain.entities.Employe}
 */
public class EmployeDto extends ClassicDto {

    /**
     * Nom du poste occupe
     */
    private String libellePoste;

    /**
     * utilisateur associé à cet employe
     */
    @NotNull
    private Long utilisateurId;

    /**
     * Contacts parmis les candidats
     */
    private Set<Long> contactsId = new HashSet<>();

    /**
     * nom de l'employé
     */
    @NotNull
    private String nom;

    /**
     * prénom de l'employé
     */
    @NotNull
    private String prenom;

    /**
     * entreprise à laquelle appartient l'employe
     */
    @NotNull
    private Long entrepriseId;

    /**
     * liste d'acteurs
     */
    private Set<Long> acteursId = new HashSet<>();

    /**
     * annonce  dont l'employe est l'auteur
     */
    private Set<Long> annoncesId = new HashSet<>();

    public String getLibellePoste() {
        return libellePoste;
    }

    public void setLibellePoste(String libellePoste) {
        this.libellePoste = libellePoste;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Set<Long> getContactsId() {
        return contactsId;
    }

    public void setContactsId(Set<Long> contactsId) {
        this.contactsId = contactsId;
    }

    public Long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public Set<Long> getActeursId() {
        return acteursId;
    }

    public void setActeursId(Set<Long> acteursId) {
        this.acteursId = acteursId;
    }

    public Set<Long> getAnnoncesId() {
        return annoncesId;
    }

    public void setAnnoncesId(Set<Long> annoncesId) {
        this.annoncesId = annoncesId;
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

    @Override
    public String toString() {
        return "EmployeDto{" +
                "libellePoste='" + libellePoste + '\'' +
                ", utilisateurId=" + utilisateurId +
                ", contactsId=" + contactsId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", entrepriseId=" + entrepriseId +
                ", acteursId=" + acteursId +
                ", annoncesId=" + annoncesId +
                '}';
    }
}
