package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.EmployeDto;
import fr.imie.poromeetlink.service.services.EmployeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.EMPLOYE)
public class EmployeController extends BaseController<EmployeDto, EmployeService> {

    @Override
    public ResponseEntity<EmployeDto> save(@RequestBody EmployeDto item) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(service.saveOne(item));
    }

    @Override
    public ResponseEntity<EmployeDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException, WrongOwnerException {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<EmployeDto> update(@RequestBody EmployeDto item) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(service.updateOne(item));
    }

    @RequestMapping(path = "/byEntreprise" + UrlConstants.ID_URL, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<EmployeDto>> getEmployesEntreprise(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(service.getAllEmployesByEntreprise(id));
    }

	@Override
	List<EmployeDto> get() {
		return service.getAll();
	}
}
