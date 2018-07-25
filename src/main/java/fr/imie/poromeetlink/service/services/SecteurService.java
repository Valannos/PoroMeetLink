package fr.imie.poromeetlink.service.services;


import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InsuffisantRightsException;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


/**
 * Interface de service pour {@link fr.imie.poromeetlink.domain.entities.Secteur}
 */
public interface SecteurService extends BaseService<SecteurDto, Long> {

    SecteurDto getByLibelle(String libelle);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    SecteurDto saveOne(SecteurDto dto) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException;


    @Override
    List<SecteurDto> getAll();


    @Override
    SecteurDto getOne(Long id) throws EntryNotFound;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    SecteurDto updateOne(SecteurDto dto) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMINISTRATEUR_SITE')")
    Boolean delete(Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException;

    Boolean exists(Long id);
}
