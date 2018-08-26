package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.service.dto.CompetenceDto;

import java.util.List;

public interface CompetenceService extends BaseService<CompetenceDto, Long> {

    /**
     * Service to retrieve all competences related to a secteur
     * @param id id of a secteur
     * @return a list of competences
     * @throws EntryNotFound thrown if passed id does not match with any secteur
     */
    List<CompetenceDto> getAllByService(Long id) throws EntryNotFound;

}
