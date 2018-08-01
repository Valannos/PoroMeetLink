package fr.imie.poromeetlink.web.controllers;

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
public class TestUtilisateurController {

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
            int response =  mvc.perform(MockMvcRequestBuilders.get("/api/secteur")).andReturn().getResponse().getStatus();
            Assert.assertEquals(response, HttpStatus.UNAUTHORIZED.value());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}