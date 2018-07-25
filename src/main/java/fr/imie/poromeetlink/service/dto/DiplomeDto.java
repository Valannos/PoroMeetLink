package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.outils.enumerations.StatutDiplomeEnum;

import javax.validation.constraints.NotNull;
import java.time.Year;

public class DiplomeDto extends ClassicDto {

    @NotNull
    private String intitule;


    private Year anneeObtention;


    private String etablissement;


    private StatutDiplomeEnum diplomeEnum;

    @NotNull
    private TypeDiplomeDto typeDiplome;

    @NotNull
    private Long idCandidat;

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

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }

    public TypeDiplomeDto getTypeDiplome() {
        return typeDiplome;
    }

    public void setTypeDiplome(TypeDiplomeDto typeDiplome) {
        this.typeDiplome = typeDiplome;
    }
}
