package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.domain.entities.CompetenceCandidatId;

/**
 * Classe DTO associée à l'entité {@link fr.imie.poromeetlink.domain.entities.CompetenceCandidat}
 */
public class CompetenceCandidatDto extends BaseEntityDto {

    private CompetenceCandidatId id;

    private CompetenceDto competence;

    private Long idCandidat;

    private Short niveau;


    public Short getNiveau() {
        return niveau;
    }

    public void setNiveau(Short niveau) {
        this.niveau = niveau;
    }

    public CompetenceCandidatId getId() {
        return id;
    }

    public void setId(CompetenceCandidatId id) {
        this.id = id;
    }

    public CompetenceDto getCompetence() {
        return competence;
    }

    public void setCompetence(CompetenceDto competence) {
        this.competence = competence;
    }

    public Long getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Long idCandidat) {
        this.idCandidat = idCandidat;
    }
}
