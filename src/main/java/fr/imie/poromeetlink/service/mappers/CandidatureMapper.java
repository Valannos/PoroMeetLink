package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Candidature;
import fr.imie.poromeetlink.service.dto.CandidatureDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PropositionCandidatureMapper.class, MessageMapper.class})
public interface CandidatureMapper {

    Candidature dtoToCandidature(CandidatureDto dto);

    CandidatureDto candidatureToDto(Candidature candidature);

    List<Candidature> dtoToCandidature(List<CandidatureDto> dto);

    List<CandidatureDto> candidatureToDto(List<Candidature> candidature);
}
