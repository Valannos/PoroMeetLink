package fr.imie.poromeetlink.domain.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * {@link javax.persistence.Id} composite pour {@link Contact}
 * @see Employe
 * @see Contact
 * @see Candidat
 */
@Embeddable
public class ContactId implements Serializable {

    @NotNull
    private Long idCandidat;

    @NotNull
    private Long idEmploye;

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Long getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Long idEmploye) {
        this.idEmploye = idEmploye;
    }

    @Override
    public boolean equals(Object obj) {

        boolean areEquals = false;

        if (obj!=null && obj instanceof ContactId) {

            ContactId autreObj = (ContactId) obj;

            areEquals = this.getIdCandidat().equals(autreObj.getIdCandidat()) && this.getIdEmploye().equals(autreObj.getIdEmploye());

        }
        return areEquals;

    }

    @Override
    public int hashCode() {
        return this.idCandidat.hashCode() + this.idEmploye.hashCode();
    }
}
