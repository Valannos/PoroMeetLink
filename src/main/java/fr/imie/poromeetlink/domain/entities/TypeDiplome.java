package fr.imie.poromeetlink.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "type_diplome")
public class TypeDiplome extends ClassicEntity {

    @NotNull
    @Column(name = "intitule", unique = true)
    private String intitule;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}
