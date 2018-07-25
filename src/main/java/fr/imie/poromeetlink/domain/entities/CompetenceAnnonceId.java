package fr.imie.poromeetlink.domain.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * {@link javax.persistence.Id} composite pour l'entité {@link CompetenceAnnonce}
 */
@Embeddable
public class CompetenceAnnonceId implements Serializable {
    private static final long serialVersionUID = -5868995617469426050L;

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

    @Override
    public int hashCode() {
        return this.idAnnonce.hashCode() + this.idCompetence.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        boolean sontEgaux = false;

        if (obj!=null && obj instanceof CompetenceAnnonceId) {

            CompetenceAnnonceId autreObj = (CompetenceAnnonceId) obj;

            sontEgaux = this.getIdAnnonce().equals(autreObj.getIdAnnonce()) && this.getIdCompetence().equals(autreObj.getIdCompetence());

        }
        return sontEgaux;
    }
}
