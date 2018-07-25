package fr.imie.poromeetlink.domain.entities;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Entity} pour gérer les entreprises
 */
@Entity
@Table(name = "entreprise")
public class Entreprise extends ClassicEntity implements Serializable {

    /**
     * serial
     */
    private static final long serialVersionUID = 761373631781012351L;

    @NotNull
    @Column(name = "intitule", nullable = false)
    private String intitule;

    @Column
    @Pattern(regexp = EntityUtils.SIRET)
    private String siret;

    @Column
    private String adresse;

    @Column
    @Pattern(regexp = EntityUtils.PHONE)
    private String telephone;

    @ManyToMany
    @JoinTable(name = "secteurs_entreprises",
            joinColumns = @JoinColumn(name = "id_entreprise"),
            inverseJoinColumns = @JoinColumn(name = "id_secteur"))
    private Set<Secteur> secteurs = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "entreprise")
    private Set<Employe> employes = new HashSet<>();

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(Set<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}
