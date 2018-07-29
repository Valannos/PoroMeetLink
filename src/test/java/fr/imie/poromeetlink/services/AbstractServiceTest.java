package fr.imie.poromeetlink.services;

import fr.imie.poromeetlink.Application;
import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.domain.repositories.*;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.mappers.CompetenceMapper;
import fr.imie.poromeetlink.service.mappers.SecteurMapper;
import fr.imie.poromeetlink.service.services.BaseService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe abstraite que chaque tests de service doit hériter.
 *
 * @param <T> Le service testé
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public abstract class AbstractServiceTest<T extends BaseService, U> {

    // Service

    @Autowired
    protected T service;

    // Mapper

    @Autowired
    protected U mapper;

    //Repositories

    @Autowired
    protected CompetenceRepository competenceRepository;

    @Autowired
    protected DiplomeRepository diplomeRepository;

    @Autowired
    protected EmployeRepository employeRepository;

    @Autowired
    protected EntrepriseRepository entrepriseRepository;

    @Autowired
    protected ExperienceRepository experienceRepository;

    @Autowired
    protected MessageRepository messageRepository;

    @Autowired
    protected SecteurRepository secteurRepository;

    @Autowired
    protected UtilisateurRepository utilisateurRepository;

    // Data

    List<CompetenceDto> dtos;
    Secteur secteur;
    Secteur secteurToSave;
    Competence competence;
    Competence competenceToSave;

    // Constantes

    static final String LIBELLE_SECTEUR = "LIBELLE_SECTEUR_SERVICE";
    static final String LIBELLE_SECTEUR_TO_SAVE = "LIBELLE_SECTEUR_SERVICE_TO_SAVE";


    /**
     * Méthode lancée avant les tests, à utiliser pour instancier des objets, les sauvegarder etc...
     */
    @Before
    public void init() {
        secteur = new Secteur();
        secteur.setLibelle(LIBELLE_SECTEUR);
        secteur = secteurRepository.save(secteur);

        competence = new Competence();
        competence.setIntitule("TEST_COMPETENCE");
        competence.setSecteur(secteur);

        competence = competenceRepository.save(competence);

        competenceToSave = new Competence();
        competenceToSave.setSecteur(secteur);
        competenceToSave.setIntitule("TEST_COMPETENCE_TO_SAVE");
    }
}
