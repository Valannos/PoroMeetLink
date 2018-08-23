package fr.imie.poromeetlink.web.api;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.imie.poromeetlink.SpringIntegrationTest;
import fr.imie.poromeetlink.outils.JwtTest;
import fr.imie.poromeetlink.outils.TestConstantes;
import fr.imie.poromeetlink.outils.constantes.RoleUtils;
import fr.imie.poromeetlink.service.dto.CompetenceCandidatDto;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.service.dto.UtilisateurDto;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

public class TestAPICompetenceCandidat extends SpringIntegrationTest {

    private List<SecteurDto> secteurDtos;
    private List<CompetenceDto> competenceDtos;
    private String token;
    private SecteurDto secteurDto;
    private UtilisateurDto user;
    private CompetenceCandidatDto competenceCandidatDto;

    @Given("^an authenticated candidate opening appropriate modal to add a comeptence$")
    public void authenticated_user_get_secteurs() throws Exception {
        token = jwtTest.createJWT("cand", RoleUtils.CANDIDAT);

        // Récupération de l'utilisateur courant
        MockHttpServletResponse responseUtilisateur = mvc.perform(MockMvcRequestBuilders
                .get("/api/utilisateur/current")
                .header("Authorization", "Bearer " + token))
                .andReturn().getResponse();

        user = jsonUtilisateur.parseObject(responseUtilisateur.getContentAsString());


        // Récupération des secteurs
        MockHttpServletResponse responseSecteurs = mvc.perform(MockMvcRequestBuilders
                .get("/api/secteur")
                .header("Authorization", "Bearer " + token))
                .andReturn()
                .getResponse();
        secteurDtos = jsonSecteurs.parseObject(responseSecteurs.getContentAsString());
    }

    @When("^selecting a secteur$")
    public void select_a_secteur() throws Exception {

        // Choix d'un secteur
        secteurDto = secteurDtos.stream()
                .filter(sec -> sec.getLibelle().equals(TestConstantes.LIBELLE_SECTEUR_SAVED_DEVELOPPEMENT))
                .findFirst().get();


    }

    @Then("^getting related competences$")
    public void getting_related_competences() throws Exception {
        // Récupération des coméptences du secteurs
        MockHttpServletResponse responseCompetence =  mvc.perform(MockMvcRequestBuilders
                .get("/api/competence/bySecteur/" + secteurDto.getId())
                .header("Authorization", "Bearer " + token))
                .andReturn()
                .getResponse();
        competenceDtos = jsonCompetence.parseObject(responseCompetence.getContentAsString());
    }

    @When("^selecting a competence, a level and submitting$")
    public void select_competence_and_level_then_submit() {

        // Choix d'une compétence
        CompetenceDto competenceDto = competenceDtos.stream()
                .filter(comp -> comp.getIntitule().equals(TestConstantes.LIBELLE_COMPETENCE_SAVED_JAVA_8))
                .findFirst().get();


        competenceCandidatDto = new CompetenceCandidatDto();
        // Saisie du niveau
        competenceCandidatDto.setNiveau((short) 3);
        competenceCandidatDto.setCompetence(competenceDto);
        competenceCandidatDto.setIdCandidat(user.getCandidatId());
    }

    @Then("^candidat's competence is successfully saved$")
    public void candidate_competence_is_saved() throws Exception {
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



  //  @Test
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
