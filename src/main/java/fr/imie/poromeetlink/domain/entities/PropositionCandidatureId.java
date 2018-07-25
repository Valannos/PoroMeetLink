package fr.imie.poromeetlink.domain.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * {@link javax.persistence.Id} composite pour {@link PropositionCandidature}
 */
@Embeddable
public class PropositionCandidatureId implements Serializable {

    private static final long serialVersionUID = -110676510446307414L;

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

    public PropositionCandidatureId(Long idCandidat, Long idAnnonce) {
        this.idCandidat = idCandidat;
        this.idAnnonce = idAnnonce;
    }

    public PropositionCandidatureId() {
    }

    @Override
    public boolean equals(Object o) {
        boolean sontEgaux = false;
        if (o != null && o instanceof PropositionCandidatureId) {

            PropositionCandidatureId autreObj = (PropositionCandidatureId) o;

            sontEgaux = this.getIdAnnonce().equals(autreObj.getIdAnnonce()) && this.getIdCandidat().equals(autreObj.getIdCandidat());

        }

        return sontEgaux;
    }

    @Override
    public int hashCode() {

        return this.idCandidat.hashCode() + this.idAnnonce.hashCode();
    }
}
