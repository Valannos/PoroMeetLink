package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.CompetenceCandidat;
import fr.imie.poromeetlink.service.dto.CompetenceCandidatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompetenceMapper.class, CandidatMapper.class})
public interface CompetenceCandidatMapper {

    CompetenceCandidat dtoToCompetenceCandidat(CompetenceCandidatDto dto);

    @Mappings({
            @Mapping(source = "candidat.id", target = "idCandidat")
    })
    CompetenceCandidatDto competenceCandidatToDto(CompetenceCandidat competenceCandidat);

    List<CompetenceCandidat> dtosToCompetenceCandidats(List<CompetenceCandidatDto> dto);

    List<CompetenceCandidatDto> competenceCandidatsToDtos(List<CompetenceCandidat> competenceCandidats);
}
