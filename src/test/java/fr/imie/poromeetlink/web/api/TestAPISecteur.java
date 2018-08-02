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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestAPISecteur {

    @Autowired
    private MockMvc mvc;

    @Autowired
    JwtTest jwtTest;

    private JacksonTester<SecteurDto> json;

    private JacksonTester<List<SecteurDto>> tableJson;

    @Before
    public void initJacksonTester() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void adminGetAllTest() {


        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);
        try {
          MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders.get("/api/secteur").header("Authorization", "Bearer " + token)).andReturn().getResponse();
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
            tableJson.parse(response.getContentAsString()).getObject().forEach(secteur -> Assert.assertNotNull(secteur.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void anonymousGetAllTest() {

        try {
            int response =  mvc.perform(MockMvcRequestBuilders
                               .get("/api/secteur"))
                               .andReturn()
                               .getResponse()
                               .getStatus();
            Assert.assertEquals(response, HttpStatus.UNAUTHORIZED.value());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void adminSaveSecteur() {

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
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
            SecteurDto dtoSaved = json.parse(response.getContentAsString()).getObject();
            Assert.assertEquals(dto.getLibelle(), dtoSaved.getLibelle());
            Assert.assertNotNull(dtoSaved.getId());

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
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void adminGetSecteurById() {

        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);

        try {
            MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders
                    .get("/api/secteur/3")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse();
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
            SecteurDto dto = json.parse(response.getContentAsString()).getObject();
            Assert.assertEquals(dto.getLibelle(), "secteur_libelle");
            Assert.assertNotNull(dto.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void adminUpdateSecteur() {

        String token = jwtTest.createJWT("masteradmin", RoleUtils.ADMINISTRATEUR_SITE);

        try {
            SecteurDto dto = new SecteurDto();
            dto.setId(3L);
            dto.setLibelle("CHIMIE_ORGANIQUE");
            MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders
                    .put("/api/secteur")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json.write(dto).getJson()))
                    .andReturn()
                    .getResponse();
            Assert.assertNotNull(response);
            Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
            SecteurDto dtoUpdated = json.parse(response.getContentAsString()).getObject();
            Assert.assertEquals(dto.getLibelle(), dtoUpdated.getLibelle());
            Assert.assertNotNull(dtoUpdated.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}