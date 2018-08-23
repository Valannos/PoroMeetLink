package fr.imie.poromeetlink.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.poromeetlink.Application;
import fr.imie.poromeetlink.outils.JwtTest;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// entry point for all assertThat methods and utility methods (e.g. entry)
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestAPISecteur {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected JwtTest jwtTest;

    private JacksonTester<SecteurDto> json;

    private JacksonTester<List<SecteurDto>> tableJson;

    @Before
    public void initJacksonTester() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void test_getAll_asAdministrateurSite_returnExpectedResult() {


        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);
        try {
          MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders.get("/api/secteur").header("Authorization", "Bearer " + token)).andReturn().getResponse();
            assertThat(response).isNotNull();
            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
            tableJson.parse(response.getContentAsString()).getObject().forEach(secteur -> assertThat(secteur.getId()).isNotNull());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_getAll_asAnonymouseUser_returnUnauthorized() {

        try {
            int response =  mvc.perform(MockMvcRequestBuilders
                               .get("/api/secteur"))
                               .andReturn()
                               .getResponse()
                               .getStatus();
            assertThat(response).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test_save_asAdministrateurSite_returnExpectedResult() {

        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);

        try {
            SecteurDto dto = new SecteurDto();
            dto.setLibelle("secteur_libelle");
            MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders
                                                    .post("/api/secteur")
                                                    .header("Authorization", "Bearer " + token)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(json.write(dto).getJson()))
                                                    .andReturn()
                                                    .getResponse();
            assertThat(response).isNotNull();
            assertThat(response.getStatus()).isIn(HttpStatus.OK.value());
            SecteurDto dtoSaved = json.parse(response.getContentAsString()).getObject();
            assertThat(dtoSaved.getLibelle()).isEqualTo(dto.getLibelle());
            assertThat(dtoSaved.getId()).isNotNull();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void adminDeleteSecteur() {

        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);

        try {
           MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders
                    .delete("/api/secteur/2")
                    .header("Authorization", "Bearer " + token))
                    .andReturn()
                    .getResponse();
            assertThat(response).isNotNull();
            assertThat(response.getStatus()).isIn(HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_getById_asAdministrateurSite_returnExpectedResult() {

        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);

        try {
            MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders
                    .get("/api/secteur/1")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();
            SecteurDto dto = json.parse(response.getContentAsString()).getObject();
            assertThat(response).isNotNull();
            assertThat(response.getStatus()).isIn(HttpStatus.OK.value());
            assertThat(dto.getLibelle()).isNotNull();
            assertThat(dto.getId()).isEqualTo(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test_update_asAdministrateurSite_returnExpectedResult() {

        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);

        try {
            SecteurDto dto = new SecteurDto();
            dto.setId(1L);
            dto.setLibelle("CHIMIE_ORGANIQUE");
            MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders
                    .put("/api/secteur")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json.write(dto).getJson()))
                    .andReturn()
                    .getResponse();
            assertThat(response).isNotNull();
            assertThat(response.getStatus()).isIn(HttpStatus.OK.value());
            SecteurDto dtoUpdated = json.parse(response.getContentAsString()).getObject();
            assertThat(dtoUpdated.getLibelle()).isEqualTo(dto.getLibelle());
            assertThat(dtoUpdated.getId()).isNotNull();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}