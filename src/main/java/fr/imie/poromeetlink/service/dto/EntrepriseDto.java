package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class EntrepriseDto extends ClassicDto {

    @NotNull
    private String intitule;

    private String siret;

    private String adresse;

    private String telephone;

    private Set<SecteurDto> secteurs = new HashSet<>();

    private Set<EmployeDto> employes = new HashSet<>();

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

    public Set<SecteurDto> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(Set<SecteurDto> secteurs) {
        this.secteurs = secteurs;
    }

    public Set<EmployeDto> getEmployes() {
        return employes;
    }

    public void setEmployes(Set<EmployeDto> employes) {
        this.employes = employes;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @Override
    public String toString() {
        return "EntrepriseDto{" +
                "intitule='" + intitule + '\'' +
                ", siret='" + siret + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", secteurs=" + secteurs +
                ", employes=" + employes +
                '}';
    }
}
