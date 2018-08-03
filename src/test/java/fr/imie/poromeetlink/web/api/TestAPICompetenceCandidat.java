package fr.imie.poromeetlink.web.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.poromeetlink.Application;
import fr.imie.poromeetlink.outils.JwtTest;
import fr.imie.poromeetlink.outils.TestConstantes;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.service.dto.CompetenceCandidatDto;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
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
public class TestAPICompetenceCandidat {

    @Autowired
    private MockMvc mvc;

    @Autowired
    JwtTest jwtTest;

    private JacksonTester<CompetenceCandidatDto> jsonCompetenceCandidat;

    private JacksonTester<UtilisateurDto> jsonUtilisateur;

    private JacksonTester<List<SecteurDto>> jsonSecteurs;

    private JacksonTester<List<CompetenceDto>> jsonCompetence;

    @Before
    public void initJacksonTester() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void candidatSaveCompetenceCandidat() throws Exception {
        String token = jwtTest.createJWT("cand", RoleUtils.CANDIDAT);

        // Récupération de l'utilisateur courant
        MockHttpServletResponse responseUtilisateur = mvc.perform(MockMvcRequestBuilders
                                                          .get("/api/utilisateur/current")
                                                          .header("Authorization", "Bearer " + token))
                                                          .andReturn().getResponse();

        UtilisateurDto user = jsonUtilisateur.parseObject(responseUtilisateur.getContentAsString());


        // Récupération des secteurs
        MockHttpServletResponse responseSecteurs = mvc.perform(MockMvcRequestBuilders
                                                      .get("/api/secteur")
                                                      .header("Authorization", "Bearer " + token))
                                                      .andReturn()
                                                      .getResponse();
        List<SecteurDto> secteurDtos = jsonSecteurs.parseObject(responseSecteurs.getContentAsString());

        // Choix d'un secteur
        SecteurDto secteurDto = secteurDtos.stream()
                                           .filter(sec -> sec.getLibelle().equals(TestConstantes.LIBELLE_SECTEUR_SAVED_DEVELOPPEMENT))
                                           .findFirst().get();

        // Récupération des coméptences du secteurs
        MockHttpServletResponse responseCompetence =  mvc.perform(MockMvcRequestBuilders
                                                         .get("/api/competence/bySecteur/" + secteurDto.getId())
                                                         .header("Authorization", "Bearer " + token))
                                                         .andReturn()
                                                         .getResponse();
        List<CompetenceDto> competenceDtos = jsonCompetence.parseObject(responseCompetence.getContentAsString());

        // Choix d'une compétence
        CompetenceDto competenceDto = competenceDtos.stream()
                                                    .filter(comp -> comp.getIntitule().equals(TestConstantes.LIBELLE_COMPETENCE_SAVED_JAVA_8))
                                                    .findFirst().get();


        CompetenceCandidatDto competenceCandidatDto = new CompetenceCandidatDto();
        // Saisie du niveau
        competenceCandidatDto.setNiveau((short) 3);
        competenceCandidatDto.setCompetence(competenceDto);
        competenceCandidatDto.setIdCandidat(user.getCandidatId());


        // Enregistrement de la compétence du candidat
        MockHttpServletResponse response =  mvc.perform(MockMvcRequestBuilders
                                               .post("/api/competence_candidat")
                                               .header("Authorization", "Bearer " + token)
                                               .contentType(MediaType.APPLICATION_JSON)
                                               .content(jsonCompetenceCandidat.write(competenceCandidatDto).getJson()))
                                               .andReturn().getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

        CompetenceCandidatDto dtoSaved = jsonCompetenceCandidat.parseObject(response.getContentAsString());
        Assert.assertEquals(dtoSaved.getCompetence().getId(), competenceCandidatDto.getCompetence().getId());
        Assert.assertEquals(dtoSaved.getIdCandidat(), competenceCandidatDto.getIdCandidat());
        Assert.assertEquals(dtoSaved.getNiveau(), competenceCandidatDto.getNiveau());
        Assert.assertNotNull(dtoSaved.getId());
    }
}
