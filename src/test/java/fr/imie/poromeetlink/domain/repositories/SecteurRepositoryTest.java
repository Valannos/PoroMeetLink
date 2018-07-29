package fr.imie.poromeetlink.domain.repositories;


import fr.imie.poromeetlink.domain.entities.Secteur;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@DataJpaTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)

public class SecteurRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;


    @Autowired
    private SecteurRepository repository;

    private Secteur secteur;

    @Before
    public void init() {
        secteur = new Secteur();
        secteur.setLibelle("TEST_SECTEUR_DAO");
        testEntityManager.persistAndFlush(secteur);
    }


    @Test
    public void findByLibelleTest() {

        Secteur secteurFound = repository.findByLibelle("TEST_SECTEUR_DAO").get();
        Assertions.assertThat(secteurFound.getLibelle()).isEqualTo(secteur.getLibelle());

    }
}