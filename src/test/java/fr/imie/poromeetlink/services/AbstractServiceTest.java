//package fr.imie.poromeetlink.services;
//
//import fr.imie.poromeetlink.domain.repositories.*;
//import fr.imie.poromeetlink.service.services.BaseService;
//import org.junit.Before;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Classe abstraite que chaque tests de service doit hériter.
// *
// * @param <T> Le service testé
// */
//public abstract class AbstractServiceTest<T extends BaseService> {
//
//
//    @Autowired
//    T service;
//
//    @Autowired
//    protected CompetenceRepository competenceRepository;
//
//    @Autowired
//    protected DiplomeRepository diplomeRepository;
//
//    @Autowired
//    protected EmployeRepository employeRepository;
//
//    @Autowired
//    protected EntrepriseRepository entrepriseRepository;
//
//    @Autowired
//    protected ExperienceRepository experienceRepository;
//
//    @Autowired
//    protected MessageRepository messageRepository;
//
//    @Autowired
//    protected SecteurRepository secteurRepository;
//
//    @Autowired
//    protected UtilisateurRepository utilisateurRepository;
//
//
//    /**
//     * Méthode lancée avant les tests, à utiliser pour instancier des objets, les sauvegarder etc...
//     */
//    @Before
//    public abstract void init();
//}
