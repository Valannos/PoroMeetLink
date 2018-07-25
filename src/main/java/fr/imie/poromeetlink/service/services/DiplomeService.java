package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.service.dto.DiplomeDto;

import java.util.List;

public interface DiplomeService extends BaseService<DiplomeDto, Long> {

    List<DiplomeDto> getAllByCandidatId(Long id);
}
