package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.outils.enumerations.TypeContrat;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class ExperienceDto extends ClassicDto {

    @NotNull
    private CandidatDto candidat;

    @NotNull
    private String intitule;

    @NotNull
    private ZonedDateTime dateDebut;

    private ZonedDateTime dateFin;

    private String structure;

    private String ville;

    private TypeContrat typeContrat;

    public CandidatDto getCandidat() {
        return candidat;
    }

    public void setCandidat(CandidatDto candidat) {
        this.candidat = candidat;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
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

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }
}
