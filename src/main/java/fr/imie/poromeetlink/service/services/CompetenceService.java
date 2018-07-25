package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.service.dto.CompetenceDto;

import java.util.List;

public interface CompetenceService extends BaseService<CompetenceDto, Long> {

    List<CompetenceDto> getAllByService(Long id) throws EntryNotFound;

}
