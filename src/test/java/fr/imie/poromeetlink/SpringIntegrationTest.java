package fr.imie.poromeetlink;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.poromeetlink.Application;
import fr.imie.poromeetlink.outils.JwtTest;
import fr.imie.poromeetlink.service.dto.CompetenceCandidatDto;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

//@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SpringIntegrationTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected JwtTest jwtTest;

    protected JacksonTester<CompetenceCandidatDto> jsonCompetenceCandidat;
    protected JacksonTester<UtilisateurDto> jsonUtilisateur;
    protected JacksonTester<List<SecteurDto>> jsonSecteurs;
    protected JacksonTester<List<CompetenceDto>> jsonCompetence;

    public SpringIntegrationTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }
}
