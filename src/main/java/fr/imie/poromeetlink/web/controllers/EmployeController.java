package fr.imie.poromeetlink.web.controllers;

import fr.imie.poromeetlink.outils.constantes.EntityUtils;
import fr.imie.poromeetlink.outils.constantes.UrlConstants;
import fr.imie.poromeetlink.outils.exceptions.*;
import fr.imie.poromeetlink.service.dto.EmployeDto;
import fr.imie.poromeetlink.service.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/" + EntityUtils.EMPLOYE)
public class EmployeController implements BaseController<EmployeDto> {

    @Autowired
    EmployeService employeService;

    @Override
    public List<EmployeDto> get() {
        return employeService.getAll();
    }

    @Override
    public ResponseEntity<EmployeDto> save(@RequestBody EmployeDto item) throws InvalidRoleException, InvalidFieldException, NoSuchFieldException, InsuffisantRightsException, WrongOwnerException, DuplicateEntryException, EntryNotFound {
        return ResponseEntity.ok(employeService.saveOne(item));
    }

    @Override
    public ResponseEntity<EmployeDto> get(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(employeService.getOne(id));
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id) throws EntryNotFound, InvalidFieldException, InvalidRoleException, WrongOwnerException {
        return ResponseEntity.ok(employeService.delete(id));
    }

    @Override
    public ResponseEntity<EmployeDto> update(@RequestBody EmployeDto item) throws EntryNotFound, InvalidFieldException, NoSuchFieldException, WrongOwnerException, InvalidRoleException, InsuffisantRightsException {
        return ResponseEntity.ok(employeService.updateOne(item));
    }

    @RequestMapping(path = "/byEntreprise" + UrlConstants.ID_URL, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<EmployeDto>> getEmployesEntreprise(@PathVariable Long id) throws EntryNotFound, WrongOwnerException {
        return ResponseEntity.ok(employeService.getAllEmployesByEntreprise(id));
    }
}
