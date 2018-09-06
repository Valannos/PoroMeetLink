package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Entreprise;
import fr.imie.poromeetlink.service.dto.EntrepriseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SecteurMapper.class, EmployeMapper.class})
public interface EntrepriseMapper {

    Entreprise dtoToEntreprise(EntrepriseDto dto);

    List<Entreprise> dtoToEntreprise(List<EntrepriseDto> dto);

    EntrepriseDto entrepriseToDto(Entreprise entreprise);

    List<EntrepriseDto> entrepriseToDto(List<Entreprise> entreprises);

}
