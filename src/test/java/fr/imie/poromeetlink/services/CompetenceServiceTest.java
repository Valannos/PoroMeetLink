//package fr.imie.poromeetlink.services;
//
//import fr.imie.poromeetlink.Application;
//import fr.imie.poromeetlink.domain.entities.Competence;
//import fr.imie.poromeetlink.domain.entities.Secteur;
//import fr.imie.poromeetlink.domain.repositories.CompetenceRepository;
//import fr.imie.poromeetlink.domain.repositories.SecteurRepository;
//import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
//import fr.imie.poromeetlink.service.dto.CompetenceDto;
//import fr.imie.poromeetlink.service.mappers.CompetenceMapper;
//import fr.imie.poromeetlink.service.services.CompetenceService;
//import fr.imie.poromeetlink.service.services.MessageProvider;
//import fr.imie.poromeetlink.service.services.impl.CompetenceServiceImpl;
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
//public class CompetenceServiceTest extends AbstractServiceTest<CompetenceService> {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CompetenceServiceTest.class);
//
//    @Autowired
//    private CompetenceMapper mapper;
//
//    private List<CompetenceDto> dtos;
//    private Secteur secteur;
//    private Competence competence;
//    private Competence competenceToSave;
//
//    @Override
//    public void init() {
//
//        secteur = new Secteur();
//        secteur.setLibelle("TEST_SECTEUR_FOR_COMPETENCE");
//        secteur = secteurRepository.save(secteur);
//
//        competence = new Competence();
//        competence.setIntitule("TEST_COMPETENCE");
//        competence.setSecteur(secteur);
//
//        competence = competenceRepository.save(competence);
//
//        competenceToSave = new Competence();
//        competenceToSave.setSecteur(secteur);
//        competenceToSave.setIntitule("TEST_COMPETENCE_TO_SAVE");
//
//
//    }
//
//    @Test
//    public void testGetAll() {
//
//        dtos = service.getAll();
//        dtos.forEach(dto -> Assertions.assertThat(dto.getId() != null));
//
//    }
//
//    @Test
//    public void testGetOne() {
//        LOGGER.info("testGetOne");
//        try {
//            CompetenceDto dto = service.getOne(competence.getId());
//            assertThat(dto.getIntitule().equals(competence.getIntitule()));
//            assertThat(dto.getSecteur().getId().equals(competence.getSecteur().getId()));
//        } catch (Exception e) {
//            assertThat(e instanceof EntryNotFound);
//        }
//
//
//    }
//
//    @Test
//    public void testSave() {
//
//        CompetenceDto dto = mapper.competenceToDto(competenceRepository.save(competenceToSave));
//        assertThat(dto.getIntitule().equals(competenceToSave.getIntitule()));
//        assertThat(competenceToSave.getId() != null);
//
//    }
//
//
//}
