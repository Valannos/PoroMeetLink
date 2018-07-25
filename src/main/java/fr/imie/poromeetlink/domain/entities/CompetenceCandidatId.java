package fr.imie.poromeetlink.domain.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * {@link javax.persistence.Id} composite pour l'entité {@link CompetenceCandidat}
 */
@Embeddable
public class CompetenceCandidatId implements Serializable {

    private static final long serialVersionUID = -2115978265224424761L;

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

    public CompetenceCandidatId() {

    }

    public CompetenceCandidatId(@NotNull Long idCompetence, @NotNull Long idCandidat) {
        this.idCompetence = idCompetence;
        this.idCandidat = idCandidat;
    }

    @Override
    public boolean equals(Object obj) {

        boolean sontEgaux = false;

        if (obj!=null && (obj instanceof CompetenceCandidatId)) {

            CompetenceCandidatId autreObj = (CompetenceCandidatId) obj;

            sontEgaux = this.getIdCandidat().equals(autreObj.getIdCandidat()) && this.getIdCompetence().equals(autreObj.getIdCompetence());

        }
        return sontEgaux;
    }

    @Override
    public int hashCode() {
        return idCandidat.hashCode() + idCompetence.hashCode();
    }
}
