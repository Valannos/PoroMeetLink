package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotNull;

/**
 * @deprecated
 * finally not employed
 */
public class CompetenceCandidatIdDto {

    @NotNull
    private Long idCompetence;

    @NotNull
    private Long idCandidat;

    public Long getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(Long idCompetence) {
        this.idCompetence = idCompetence;
    }

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }
}
