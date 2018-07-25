//package fr.imie.poromeetlink.services;
//
//import fr.imie.poromeetlink.Application;
//import fr.imie.poromeetlink.domain.entities.Secteur;
//import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
//import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
//import fr.imie.poromeetlink.service.dto.SecteurDto;
//import fr.imie.poromeetlink.service.mappers.SecteurMapper;
//import fr.imie.poromeetlink.service.services.SecteurService;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.transaction.Transactional;
//
///**
// * Tests pour {@link Secteur}
// */
//@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
//public class SecteurServiceTest extends AbstractServiceTest<SecteurService> {
//
//    @Autowired
//    SecteurMapper mapper;
//
//    Secteur secteur;
//
//    @Override
//    public void init() {
//        secteur = new Secteur();
//        secteur.setLibelle("BIOCHIMIE");
//    }
//
//    @Test
//    public void testGetByIntitule() {
//
//        String libelle = "CHIMIE";
//        Assertions.assertThat(service.getByLibelle(libelle).getLibelle()).isEqualTo(libelle);
//
//    }
//
//    @Test
//    public void testGetAll() {
//
//        service.getAll().forEach(secteurDto ->
//                Assertions.assertThat(secteurDto.getId() != null)
//        );
//    }
//
//    @Test
//    public void testSave() {
//
//        try {
//            SecteurDto dto = service.saveOne(mapper.secteurToDto(secteur));
//            Assertions.assertThat(dto.getId()).isNotNull();
//        } catch (InvalidRoleException | InvalidFieldException | NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
