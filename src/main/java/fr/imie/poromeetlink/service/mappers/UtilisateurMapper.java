package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.Utilisateur;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MessageMapper.class, RoleMapper.class, EmployeMapper.class, CandidatMapper.class})
public interface UtilisateurMapper {

    Utilisateur dtoToUtilisateur(UtilisateurDto dto);


    @Mappings({
            @Mapping(source = "candidat.id", target = "candidatId"),
            @Mapping(source = "employe.id", target = "employeId")
    })
    UtilisateurDto utilisateurToDto(Utilisateur utilisateur);

    List<Utilisateur> dtoToUtilisateur(List<UtilisateurDto> dto);

    List<UtilisateurDto> utilisateurToDto(List<Utilisateur> utilisateur);


}
