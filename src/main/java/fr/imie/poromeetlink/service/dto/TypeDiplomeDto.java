package fr.imie.poromeetlink.service.dto;

/**
 * DTO for {@link fr.imie.poromeetlink.domain.entities.TypeDiplome}
 */
public class TypeDiplomeDto extends ClassicDto {

    /**
     * intitule
     */
    private String intitule;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}
