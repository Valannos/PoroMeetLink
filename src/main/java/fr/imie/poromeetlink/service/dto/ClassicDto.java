package fr.imie.poromeetlink.service.dto;

/**
 * Classe DTO à hériter sauf pour les DTO contenant une clé composite
 */
public abstract class ClassicDto extends BaseEntityDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
