package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Employe;
import fr.imie.poromeetlink.service.dto.EmployeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ActeurMapper.class, AnnonceMapper.class})
public interface EmployeMapper {

    @Mappings({
            @Mapping(source = "employe.utilisateur.id", target = "utilisateurId"),
            @Mapping(source = "entreprise.id", target = "entrepriseId"),
    })
    EmployeDto employeToDto(Employe employe);

    List<EmployeDto> employeToDto(List<Employe> employes);

    Employe dtoToEmploye(EmployeDto dto);

    List<Employe> dtoToEmploye(List<EmployeDto> dto);

}
