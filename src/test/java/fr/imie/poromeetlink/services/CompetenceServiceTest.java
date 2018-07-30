package fr.imie.poromeetlink.services;

import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.outils.TestConstantes;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.mappers.CompetenceMapper;
import fr.imie.poromeetlink.service.services.CompetenceService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetenceServiceTest extends AbstractServiceTest<CompetenceService, CompetenceMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompetenceServiceTest.class);

    @Test
    public void testGetAll() {

        dtos = service.getAll();
        dtos.forEach(dto -> Assertions.assertThat(dto.getId() != null));

    }

    @Test
    public void testGetOne() {
        try {
            CompetenceDto dto = service.getOne(competence.getId());
            assertThat(dto.getIntitule().equals(competence.getIntitule()));
            assertThat(dto.getSecteur().getId().equals(competence.getSecteur().getId()));
        } catch (Exception e) {
            assertThat(e instanceof EntryNotFound);
        }


    }

    @Test
    public void testSave() {
        secteurToSave = new Secteur();
        secteurToSave.setLibelle(TestConstantes.LIBELLE_SECTEUR_TO_SAVE);
        secteurToSave = secteurRepository.save(secteurToSave);

        competenceToSave = new Competence();
        competenceToSave.setSecteur(secteurToSave);
        competenceToSave.setIntitule(TestConstantes.LIBELLE_COMPETENCE_TO_SAVE);

        CompetenceDto dto = mapper.competenceToDto(competenceRepository.save(competenceToSave));
        assertThat(dto.getIntitule().equals(competenceToSave.getIntitule()));
        assertThat(competenceToSave.getId() != null);

    }


}
