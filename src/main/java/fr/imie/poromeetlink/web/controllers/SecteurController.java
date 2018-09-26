package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.exceptions.EntryNotFound;
import fr.imie.poromeetlink.outils.exceptions.InsuffisantRightsException;
import fr.imie.poromeetlink.outils.exceptions.InvalidFieldException;
import fr.imie.poromeetlink.outils.exceptions.InvalidRoleException;
import fr.imie.poromeetlink.outils.exceptions.NullDataTransfertObject;
import fr.imie.poromeetlink.service.dto.SecteurDto;
import fr.imie.poromeetlink.domain.entities.Secteur;
import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.service.services.SecteurService;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.SECTEUR)
public class SecteurController extends BaseController<SecteurDto, SecteurService> {

	@Override
	public ResponseEntity<SecteurDto> save(@Valid @RequestBody SecteurDto secteur)
			throws InvalidRoleException, InvalidFieldException, NoSuchFieldException {
		this.checkIfNull(secteur);
		return ResponseEntity.ok(service.saveOne(secteur));

	}

	@Override
	public ResponseEntity<SecteurDto> get(@PathVariable Long id) throws EntryNotFound {
		return ResponseEntity.ok(service.getOne(id));
	}

	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id)
			throws InvalidRoleException, EntryNotFound, InvalidFieldException {
		return ResponseEntity.ok(service.delete(id));
	}

	@Override
	public ResponseEntity<SecteurDto> update(@Valid @RequestBody SecteurDto secteur)
			throws NoSuchFieldException, EntryNotFound, InvalidFieldException, InsuffisantRightsException {
		this.checkIfNull(secteur);
		return ResponseEntity.ok(service.updateOne(secteur));
	}

	@Override
	List<SecteurDto> get() {
		return service.getAll();
	}
}
