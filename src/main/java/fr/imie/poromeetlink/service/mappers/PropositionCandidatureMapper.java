package fr.imie.poromeetlink.service.mappers;

import fr.imie.poromeetlink.domain.entities.PropositionCandidature;
import fr.imie.poromeetlink.service.dto.PropositionCandidatureDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CandidatMapper.class, CandidatureMapper.class, AnnonceMapper.class})
public interface PropositionCandidatureMapper {


    PropositionCandidature dtoToPropositionCandidature(PropositionCandidatureDto dto);

    @Mappings({
            @Mapping(source = "candidat.id", target = "idCandidat"),
            @Mapping(source = "annonce.id", target = "idAnnonce"),
            @Mapping(source = "candidature.id", target = "idCandidature")
    })
    PropositionCandidatureDto propositionCandidatureToDto(PropositionCandidature propositionCandidature);

    List<PropositionCandidature> dtoToPropositionCandidature(List<PropositionCandidatureDto> dto);

    List<PropositionCandidatureDto> propositionCandidatureToDto(List<PropositionCandidature> propositionCandidature);

}
