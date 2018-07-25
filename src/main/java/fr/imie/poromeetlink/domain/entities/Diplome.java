package fr.imie.poromeetlink.domain.entities;

import fr.imie.poromeetlink.outils.enumerations.StatutDiplomeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Year;

/**
 * Entité pour gérer les diplômes
 */
@Entity
@Table(name = "diplome")
public class Diplome extends ClassicEntity implements Serializable {

    private static final long serialVersionUID = -6290881147868390733L;

    @NotNull
    @Column
    private String intitule;

    @Column(name = "annee_obtention")
    private Year anneeObtention;

    @Column
    private String etablissement;

    @Enumerated(EnumType.ORDINAL)
    private StatutDiplomeEnum diplomeEnum;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "diplome_type_diplome",
            joinColumns = @JoinColumn(name = "id_diplome"),
            inverseJoinColumns = @JoinColumn(name = "id_type_diplome"))
    private TypeDiplome typeDiplome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_candidat", nullable = false)
    private Candidat candidat;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Year getAnneeObtention() {
        return anneeObtention;
    }

    public void setAnneeObtention(Year anneeObtention) {
        this.anneeObtention = anneeObtention;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public StatutDiplomeEnum getDiplomeEnum() {
        return diplomeEnum;
    }

    public void setDiplomeEnum(StatutDiplomeEnum diplomeEnum) {
        this.diplomeEnum = diplomeEnum;
    }

    public TypeDiplome getTypeDiplome() {
        return typeDiplome;
    }

    public void setTypeDiplome(TypeDiplome typeDiplome) {
        this.typeDiplome = typeDiplome;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
}
