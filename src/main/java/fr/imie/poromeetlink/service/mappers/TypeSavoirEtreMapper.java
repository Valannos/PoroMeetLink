package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.TypeSavoirEtre;
import fr.imie.poromeetlink.service.dto.TypeSavoirEtreDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeSavoirEtreMapper {

    TypeSavoirEtre dtoToTypeSavoirEtre(TypeSavoirEtreDto typeSavoirEtreDto);

    List<TypeSavoirEtre> dtoToTypeSavoirEtre(List<TypeSavoirEtreDto> typeSavoirEtreDtos);

    TypeSavoirEtreDto typeSavoirEtreToDto(TypeSavoirEtre typeSavoirEtre);

    List<TypeSavoirEtreDto> typeSavoirEtreToDto(List<TypeSavoirEtreDto> typeSavoirEtreDtos);

}
