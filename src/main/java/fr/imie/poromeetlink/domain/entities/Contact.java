package fr.imie.poromeetlink.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Gère les contacts entre {@link Employe} et {@link Candidat}
 */
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @EmbeddedId
    private ContactId id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId("idCandidat")
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId("idEmploye")
    @JoinColumn(name = "id_employe")
    private Employe employe;

    private ZonedDateTime dateEtablissement;

    private Boolean bloqueParCandidat;

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public ZonedDateTime getDateEtablissement() {
        return dateEtablissement;
    }

    public void setDateEtablissement(ZonedDateTime dateEtablissement) {
        this.dateEtablissement = dateEtablissement;
    }

    public Boolean getBloque() {
        return bloqueParCandidat;
    }

    public void setBloque(Boolean bloque) {
        this.bloqueParCandidat = bloque;
    }
}
