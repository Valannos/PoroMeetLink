package fr.imie.poromeetlink.service.dto;

import javax.validation.constraints.NotNull;

/**
 * Classe DTO associée à la clé composite {@link fr.imie.poromeetlink.domain.entities.CompetenceAnnonceId}
 */
public class CompetenceAnnonceIdDto {

    @NotNull
    private Long idAnnonce;

    @NotNull
    private Long idCompetence;

    public Long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Long idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Long getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(Long idCompetence) {
        this.idCompetence = idCompetence;
    }
}
