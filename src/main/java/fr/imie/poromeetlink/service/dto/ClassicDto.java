package fr.imie.poromeetlink.service.dto;

/**
 * A DTO abstract class that "classic" DTOs must extend i.e. without composite key.
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
