package fr.imie.poromeetlink.domain.repositories;


import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.outils.TestConstantes;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

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
        secteur.setLibelle("BIOLOGIE");
        testEntityManager.persistAndFlush(secteur);
    }


    @Test
    public void findByLibelleTest() {

        Secteur secteurFound = repository.findByLibelle("BIOLOGIE").get();
        Assertions.assertThat(secteurFound.getLibelle()).isEqualTo(secteur.getLibelle());

    }


    @Test
    public void getAll() {

        List<Secteur> secteurs = repository.findAll();
        secteurs.forEach(secteur -> Assertions.assertThat(secteur.getId()));
    }

    @Test
    public void save() {

        secteur = new Secteur();
        secteur.setLibelle(TestConstantes.LIBELLE_SECTEUR_TO_SAVE_MAGIE);
        Secteur secteurSaved = repository.save(secteur);
        Assert.assertNotNull(secteurSaved.getId());

    }
    @Test
    public void saveError() {

        secteur = new Secteur();
        try {
            Secteur secteurSaved = repository.save(secteur);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ConstraintViolationException);
        }
    }

    @Test
    public void udate() {

        secteur = repository.findByLibelle("BIOLOGIE").get();
        secteur.setLibelle(TestConstantes.LIBELLE_SECTEUR_TO_SAVE_MAGIE);
        try {
            Secteur secteurSaved = repository.save(secteur);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ConstraintViolationException);
        }
    }


}