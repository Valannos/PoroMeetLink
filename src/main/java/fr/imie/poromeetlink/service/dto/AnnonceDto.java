package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.outils.enumerations.TypeContrat;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link fr.imie.poromeetlink.domain.entities.Annonce}
 */
public class AnnonceDto extends ClassicDto {

    private String intitule;

    @NotNull
    private ZonedDateTime dateCreation;

    private ZonedDateTime dateDebut;

    private String niveauExperience;

    private ZonedDateTime dateFin;

    private Boolean valid;

    private List<Long> acteursId = new ArrayList<>();

    @NotNull
    private String description;

    private List<CompetenceAnnonceDto> competenceAnnonce = new ArrayList<>();

    private TypeContrat typeContrat;

    @NotNull
    private Long employeId;

    private Set<Long> propositionCandidaturesId = new HashSet<>();

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

    public String getNiveauExperience() {
        return niveauExperience;
    }

    public void setNiveauExperience(String niveauExperience) {
        this.niveauExperience = niveauExperience;
    }

    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Set<Long> getPropositionCandidaturesId() {
        return propositionCandidaturesId;
    }

    public void setPropositionCandidaturesId(Set<Long> propositionCandidaturesId) {
        this.propositionCandidaturesId = propositionCandidaturesId;
    }

    public List<Long> getActeursId() {
        return acteursId;
    }

    public void setActeursId(List<Long> acteursId) {
        this.acteursId = acteursId;
    }

    public List<CompetenceAnnonceDto> getCompetenceAnnonce() {
        return competenceAnnonce;
    }

    public void setCompetenceAnnonce(List<CompetenceAnnonceDto> competenceAnnonce) {
        this.competenceAnnonce = competenceAnnonce;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
