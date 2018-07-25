package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.CompetenceAnnonce;
import fr.imie.poromeetlink.service.dto.CompetenceAnnonceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompetenceMapper.class, AnnonceMapper.class})
public interface CompetenceAnnonceMapper {


    CompetenceAnnonce dtoToCompetenceAnnonce(CompetenceAnnonceDto dto);

    @Mappings({
            @Mapping(source = "annonce.id", target = "idAnnonce")
    })
    CompetenceAnnonceDto competenceAnnonceToDto(CompetenceAnnonce competenceAnnonce);

    List<CompetenceAnnonce> dtoToCompetenceAnnonce(List<CompetenceAnnonceDto> dto);

    List<CompetenceAnnonceDto> competenceAnnonceToDto(List<CompetenceAnnonce> competenceAnnonce);

}
