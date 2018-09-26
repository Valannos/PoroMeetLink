package fr.imie.poromeetlink.service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.imie.poromeetlink.domain.entities.Competence;
import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.domain.repositories.CompetenceRepository;
import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.service.dto.CompetenceDto;
import fr.imie.poromeetlink.service.mappers.CompetenceMapper;
import fr.imie.poromeetlink.service.services.CompetenceService;
import fr.imie.poromeetlink.service.services.SecteurService;

@Service
public class CompetenceServiceImpl extends AbstractService<CompetenceRepository> implements CompetenceService {

	private final CompetenceMapper competenceMapper;

	private final SecteurService secteurService;
	
	@Autowired
	public CompetenceServiceImpl(CompetenceMapper competenceMapper, SecteurService secteurService) {

		this.competenceMapper = competenceMapper;
		this.secteurService = secteurService;
	}

	@Override
	public List<CompetenceDto> getAll() {

		return competenceMapper.competencesToDtos(repository.findAll());
	}

	@Override
	public CompetenceDto getOne(Long id) throws EntryNotFound {

		if (id == null) {
			throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Competence.class));
		}

		Optional<Competence> competence = repository.findById(id);

		if (competence.isPresent()) {
			return competenceMapper.competenceToDto(competence.get());
		} else {
			throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Competence.class));

		}
	}

	@Override
	public CompetenceDto saveOne(CompetenceDto dto) throws InvalidFieldException {
		
		Competence competence = repository.save(competenceMapper.dtoToCompetence(dto));

		return competenceMapper.competenceToDto(competence);
	}

	@Override
	public CompetenceDto updateOne(CompetenceDto dto) throws EntryNotFound, InvalidFieldException {

		if (dto != null) {
			if (repository.existsById(dto.getId())) {

				Competence competence = repository.save(competenceMapper.dtoToCompetence(dto));

				dto = competenceMapper.competenceToDto(competence);

			} else {

				throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Competence.class));
			}
		} else {
			throw new NullDataTransfertObject(
					messageProvider.getMessage("NULL_DATA_TRANSFERT_OBJECT", Competence.class));
		}

		return dto;
	}

	@Override
	public Boolean delete(Long id) throws EntryNotFound {

		if (id == null) {
			throw new IllegalArgumentException(messageProvider.getMessage("NULL_ID", Competence.class));
		}

		if (repository.existsById(id)) {
			repository.deleteById(id);

		} else {
			throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Competence.class));
		}
		return true;
	}

	@Override
	public void validator(CompetenceDto dto) {
	}

	@Override
	public List<CompetenceDto> getAllByService(Long id) throws EntryNotFound {

		if (!secteurService.exists(id)) {
			throw new EntryNotFound(messageProvider.getMessage("ENTRY_NOT_FOUND", Secteur.class));
		} else {
			List<Competence> competences = new ArrayList<>(repository.getAllBySecteurId(id));
			return competenceMapper.competencesToDtos(competences);
		}
	}
}
