package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Experience;
import fr.imie.poromeetlink.service.dto.ExperienceDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CandidatMapper.class}, componentModel = "spring")
public interface ExperienceMapper {

    Experience dtoToExperience(ExperienceDto dto);

    List<Experience> dtoToExperience(List<ExperienceDto> dtos);

    ExperienceDto experienceToDto(Experience experience);

    List<ExperienceDto> experienceToDto(List<Experience> experience);

}
