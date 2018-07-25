package fr.imie.poromeetlink.service.mappers;


import fr.imie.poromeetlink.domain.entities.SavoirEtre;
import fr.imie.poromeetlink.service.dto.SavoirEtreDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeSavoirEtreMapper.class})
public interface SavoirEtreMapper {

    SavoirEtre dtoToSavoirEtre(SavoirEtreDto savoirEtre);

    List<SavoirEtre> dtoToSavoirEtre(List<SavoirEtreDto> savoirEtre);

    SavoirEtreDto savoirEtreToDto(SavoirEtre savoirEtreDto);

    List<SavoirEtreDto> savoirEtreToDto(List<SavoirEtreDto> savoirEtreDtos);
}
