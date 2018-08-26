package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Candidat;
import fr.imie.poromeetlink.service.dto.CandidatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * {@link Mapper} for {@link Candidat} and {@link fr.imie.poromeetlink.service.dto.CandidatureDto}
 */
@Mapper(componentModel = "spring",
        uses = {DiplomeMapper.class, ExperienceMapper.class, CompetenceCandidatMapper.class, PropositionCandidatureMapper.class})
public interface CandidatMapper {

    Candidat dtoToCandidat(CandidatDto dto);

    List<Candidat> dtoToCandidat(List<CandidatDto> dtos);

    @Mappings({
            @Mapping(source = "utilisateur.id", target = "utilisateurId")
    })
    CandidatDto candidatToDto(Candidat candidat);

    List<CandidatDto> candidatToDto(List<Candidat> candidat);
}
