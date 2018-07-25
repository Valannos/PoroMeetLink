package fr.imie.poromeetlink.domain.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "savoirEtre")
public class SavoirEtre extends ClassicEntity implements Serializable {

    /**
     * serial
     */
    private static final long serialVersionUID = -5319925614973464720L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_candidat", nullable = false)
    private Candidat candidat;


    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_SavoirEtre", nullable = false)
    private TypeSavoirEtre typeSavoirEtre;


    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public TypeSavoirEtre getTypeSavoirEtre() {
        return typeSavoirEtre;
    }

    public void setTypeSavoirEtre(TypeSavoirEtre typeSavoirEtre) {
        this.typeSavoirEtre = typeSavoirEtre;
    }
}
