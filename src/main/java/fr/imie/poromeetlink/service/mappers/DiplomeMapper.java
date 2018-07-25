package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Diplome;
import fr.imie.poromeetlink.service.dto.DiplomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CandidatMapper.class, TypeDiplomeMapper.class})
public interface DiplomeMapper {

    Diplome dtoToDiplome(DiplomeDto dto);

    List<Diplome> dtoToDiplome(List<DiplomeDto> dtos);

    @Mappings({
            @Mapping(source = "candidat.id", target = "idCandidat")
    })
    DiplomeDto diplomeToDto(Diplome diplome);

    List<DiplomeDto> diplomeToDto(List<Diplome> diplomes);
}
