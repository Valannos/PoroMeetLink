package fr.imie.poromeetlink.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "typeSavoirEtre")
public class TypeSavoirEtre extends ClassicEntity implements Serializable {

    /**
     * serial
     */
    private static final long serialVersionUID = 453186421618113538L;

    @NotNull
    @Column(name = "intitule", unique = true, nullable = false)
    private String intitule;


    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}
