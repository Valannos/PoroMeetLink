package fr.imie.poromeetlink.service.dto;

/**
 * Classe DTO associée à {@link fr.imie.poromeetlink.domain.entities.PropositionCandidatureId}
 */
public class PropositionCandidatureIdDto {

    private Long idCandidat;

    private Long idAnnonce;

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
}
