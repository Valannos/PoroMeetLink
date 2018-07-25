package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Acteur;
import fr.imie.poromeetlink.service.dto.ActeurDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ActeurMapper {

    Acteur dtoToActeur(ActeurDto dto);

    @Mappings({
            @Mapping(source = "acteur.id", target = "acteurId"),
            @Mapping(source = "annonce.id", target = "annonceId")
    })
    ActeurDto acteurToDto(Acteur acteur);

    List<Acteur> dtosToActeurs(List<ActeurDto> dtos);

    List<ActeurDto> acteursToDtos(List<Acteur> acteurs);
}
