package fr.imie.poromeetlink.services;

import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.outils.TestConstantes;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.mappers.CompetenceMapper;
import fr.imie.poromeetlink.service.services.CompetenceService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetenceServiceTest extends AbstractServiceTest<CompetenceService, CompetenceMapper> {

    @Test
    public void test_getAll_ReturnExpectedResult() {
        service.getAll().forEach(dto -> assertThat(dto.getId()).isNotNull());
    }

    @Test
    public void test_getOne_ReturnExpectedResult() {
        try {
            CompetenceDto dto = service.getOne(competence.getId());
            assertThat(dto.getIntitule()).isEqualTo(competence.getIntitule());
            assertThat(dto.getSecteur().getId()).isEqualTo(competence.getSecteur().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_save_returnExpectedResult() {
        secteurToSave = new Secteur();
        secteurToSave.setLibelle(TestConstantes.LIBELLE_SECTEUR_TO_SAVE_MAGIE);
        secteurToSave = secteurRepository.save(secteurToSave);

        competenceToSave = new Competence();
        competenceToSave.setSecteur(secteurToSave);
        competenceToSave.setIntitule(TestConstantes.LIBELLE_COMPETENCE_TO_SAVE_SYNTHESE_CHIMIQUE);

        CompetenceDto dto = mapper.competenceToDto(competenceRepository.save(competenceToSave));
        assertThat(dto.getIntitule()).isEqualTo(competenceToSave.getIntitule());
        assertThat(competenceToSave.getId()).isNotNull();

    }
}
