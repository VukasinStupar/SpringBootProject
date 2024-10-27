package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentDto;
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentSearchDto;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.security.auth.TokenBasedAuthentication;
import rs.ac.uns.ftn.springsecurityexample.service.EquipmentService;

@RestController
@RequestMapping(value = "api/equipments")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService; 

	//napraviti zastitu da mora da bude companyAdmin
	@PostMapping(consumes = "application/json")
	public ResponseEntity<EquipmentDto> saveEquipment(@RequestBody EquipmentDto equipmentDto, Principal principal) {
		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
		
		Equipment equipment = equipmentService.create(equipmentDto, loggedUser);
		if (equipment == null) {
			return new ResponseEntity<EquipmentDto>(HttpStatus.BAD_REQUEST);
		} 

		EquipmentDto createDto = new EquipmentDto(equipment);
		return new ResponseEntity<EquipmentDto>(createDto, HttpStatus.CREATED);

	}

	@GetMapping("/byCompanyId/{id}")
	public ResponseEntity<ArrayList<EquipmentDto>> byCompanyId(@PathVariable("id") Long id) {
		ArrayList<Equipment> companyEquipment = equipmentService.getAllByCompanyId(id);
		
		ArrayList<EquipmentDto> dtos = new ArrayList<EquipmentDto>();
		for(Equipment equipment : companyEquipment) {
			dtos.add(new EquipmentDto(equipment));
		}
		return new ResponseEntity<ArrayList<EquipmentDto>>(dtos, HttpStatus.OK);

	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<EquipmentDto>> filter(@RequestBody EquipmentSearchDto equipmentSearchDto) {
		List<Equipment> equipments = equipmentService.searchEquipment(equipmentSearchDto);
		
		List<EquipmentDto> dtos= new ArrayList<EquipmentDto>();
		
		for(Equipment equipment: equipments) {
			EquipmentDto equipmentDto = new EquipmentDto(equipment);
			dtos.add(equipmentDto);
		}
		return new ResponseEntity<List<EquipmentDto>>(dtos, HttpStatus.OK);
		
	}
	
	@PutMapping("/deleteEquipment/{id}")
	public ResponseEntity<EquipmentDto> deleteEquipment(@PathVariable("id") Long id) {

		Equipment equipment = equipmentService.deleteEquipment(id);
		
		EquipmentDto equipmentDto = new EquipmentDto(equipment);
		return new ResponseEntity<EquipmentDto>(equipmentDto, HttpStatus.OK);
	}
	
	
	
	@GetMapping
	public ResponseEntity<ArrayList<EquipmentDto>> getAll() {
		List<Equipment> companyEquipment = equipmentService.getAll();
		
		ArrayList<EquipmentDto> dtos = new ArrayList<EquipmentDto>();
		for(Equipment equipment : companyEquipment) {
			dtos.add(new EquipmentDto(equipment));
		}
		return new ResponseEntity<ArrayList<EquipmentDto>>(dtos, HttpStatus.OK);

	}
	
	

}
