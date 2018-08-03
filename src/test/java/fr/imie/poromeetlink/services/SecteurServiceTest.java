package fr.imie.poromeetlink.services;

import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.outils.TestConstantes;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.service.mappers.SecteurMapper;
import fr.imie.poromeetlink.service.services.SecteurService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

/**
 * Tests pour {@link Secteur}
 */

public class SecteurServiceTest extends AbstractServiceTest<SecteurService, SecteurMapper> {

    @Test
    public void testService() {
        Assert.assertNotNull(service);
    }

    @Test
    public void testGetByIntitule() {


        Assertions.assertThat(service.getByLibelle(TestConstantes.LIBELLE_SECTEUR_SAVED_DEVELOPPEMENT).getLibelle()).isEqualTo(TestConstantes.LIBELLE_SECTEUR_SAVED_DEVELOPPEMENT);

    }

    @Test
    public void testGetAll() {

        service.getAll().forEach(secteurDto ->
                Assertions.assertThat(secteurDto.getId() != null)
        );
    }

    @Test
    @WithMockUser(roles="ADMINISTRATEUR_SITE")
    public void testSave() {

        secteurToSave = new Secteur();
        secteurToSave.setLibelle(TestConstantes.LIBELLE_SECTEUR_TO_SAVE_MAGIE);
        SecteurDto dto = mapper.map(secteurToSave, SecteurDto.class);

        try {
            SecteurDto secteurDto = service.saveOne(dto);
            Assertions.assertThat(secteurDto.getId()).isNotNull();
        } catch (InvalidRoleException | InvalidFieldException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}
