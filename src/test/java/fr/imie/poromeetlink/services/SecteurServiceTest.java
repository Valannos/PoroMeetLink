package fr.imie.poromeetlink.services;

import fr.imie.poromeetlink.Application;
import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.service.mappers.SecteurMapper;
import fr.imie.poromeetlink.service.services.SecteurService;
import fr.imie.poromeetlink.service.services.impl.SecteurServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

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


        Assertions.assertThat(service.getByLibelle(LIBELLE_SECTEUR).getLibelle()).isEqualTo(LIBELLE_SECTEUR);

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
        secteurToSave.setLibelle(LIBELLE_SECTEUR_TO_SAVE);
        SecteurDto dto = mapper.map(secteurToSave, SecteurDto.class);

        try {
            SecteurDto secteurDto = service.saveOne(dto);
            Assertions.assertThat(secteurDto.getId()).isNotNull();
        } catch (InvalidRoleException | InvalidFieldException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}
