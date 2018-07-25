package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.TypeDiplome;
import fr.imie.poromeetlink.service.dto.TypeDiplomeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeDiplomeMapper {

    TypeDiplome dtoToTypeDiplome(TypeDiplomeDto dto);

    List<TypeDiplome> dtosToTypeDiplomes(List<TypeDiplomeDto> dtos);

    TypeDiplomeDto typeDiplomeToDto(TypeDiplome typeDiplome);

    List<TypeDiplomeDto> typeDiplomesToDtos(List<TypeDiplome> typeDiplomes);

}
