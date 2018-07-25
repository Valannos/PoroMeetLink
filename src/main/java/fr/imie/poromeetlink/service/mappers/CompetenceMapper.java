package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SecteurMapper.class})
public interface CompetenceMapper {

    Competence dtoToCompetence(CompetenceDto dto);

    List<Competence> dtosToCompetences(List<CompetenceDto> dtos);

    CompetenceDto competenceToDto(Competence competence);

    List<CompetenceDto> competencesToDtos(List<Competence> competences);
}
