package fr.imie.poromeetlink.service.dto;

import fr.imie.poromeetlink.domain.entities.CompetenceAnnonceId;

/**
 * Classe DTO associé à {@link fr.imie.poromeetlink.domain.entities.CompetenceAnnonce}
 * N'implémente pas {@link ClassicDto} car utilise une clé composite
 */
public class CompetenceAnnonceDto extends BaseEntityDto {

    private CompetenceAnnonceId id;

    private Long idAnnonce;

    private CompetenceDto competence;

    private Short niveauRequis;

    public CompetenceAnnonceId getId() {
        return id;
    }

    public CompetenceDto getCompetence() {
        return competence;
    }

    public void setCompetence(CompetenceDto competence) {
        this.competence = competence;
    }

    public Short getNiveauRequis() {
        return niveauRequis;
    }

    public void setNiveauRequis(Short niveauRequis) {
        this.niveauRequis = niveauRequis;
    }

    public void setId(CompetenceAnnonceId id) {
        this.id = id;
    }

    public Long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Long idAnnonce) {
        this.idAnnonce = idAnnonce;
    }
}
