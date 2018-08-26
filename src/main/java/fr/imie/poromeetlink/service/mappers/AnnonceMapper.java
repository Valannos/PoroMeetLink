package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Annonce;
import fr.imie.poromeetlink.service.dto.AnnonceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * {@link Mapper} for {@link Annonce} and {@link AnnonceDto}
 */
@Mapper(componentModel = "spring",
        uses = {MessageMapper.class, CompetenceAnnonceMapper.class, PropositionCandidatureMapper.class, EmployeMapper.class, ActeurMapper.class})
public interface AnnonceMapper {

    Annonce dtoToAnnonce(AnnonceDto dto);

    List<Annonce> dtoToAnnonce(List<AnnonceDto> dto);

    @Mappings({
            @Mapping(source = "employe.id", target = "employeId")
    })
    AnnonceDto annonceToDto(Annonce annonce);

    List<AnnonceDto> annonceToDto(List<Annonce> annonces);

}
