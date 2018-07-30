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
}