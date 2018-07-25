package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.domain.entities.PropositionCandidature;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class PropositionCandidatureDto extends BaseEntityDto {

    private PropositionCandidature id;

    private Long idCandidat;

    private Long idAnnonce;

    private Long idCandidature;

    @NotNull
    private ZonedDateTime dateCreation;

    private Boolean acceptee;

    private String accroche;

    @NotNull
    private Boolean auteurCandidat;

    public PropositionCandidature getId() {
        return id;
    }

    public void setId(PropositionCandidature id) {
        this.id = id;
    }

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Long idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Long getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(Long idCandidature) {
        this.idCandidature = idCandidature;
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

    public Boolean getAuteurCandidat() {
        return auteurCandidat;
    }

    public void setAuteurCandidat(Boolean auteurCandidat) {
        this.auteurCandidat = auteurCandidat;
    }
}
