package fr.imie.poromeetlink.service.services;

import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.service.dto.EntrepriseDto;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;

/**
 * Interface de service pour {@link EntrepriseDto}
 */
public interface EntrepriseService extends BaseService<EntrepriseDto, Long> {

    @PreAuthorize("hasRole('ROLE_GERANT') or hasRole('ROLE_ADMINISTRATEUR_COMPTE')")
    @Transactional
    EntrepriseDto getEntrepriseByUtilisateur() throws EntryNotFound;

}
