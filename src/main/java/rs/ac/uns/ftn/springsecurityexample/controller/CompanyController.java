package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDto;
import rs.ac.uns.ftn.springsecurityexample.dto.CompanySearchDto;
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentStatisticsDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationDto;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.security.auth.TokenBasedAuthentication;
import rs.ac.uns.ftn.springsecurityexample.service.CompanyService;

//TODO: Paging
@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<CompanyDto> saveCompany(@RequestBody CompanyDto companyDto) {

		Company company = companyService.create(companyDto);

		CompanyDto createdDto = new CompanyDto(company);
		return new ResponseEntity<CompanyDto>(createdDto, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<CompanyDto>> getAll() {

		List<Company> companies = companyService.findAll();
		ArrayList<CompanyDto> dtos = new ArrayList<CompanyDto>();
		for (Company company : companies) {
			CompanyDto companyDto = new CompanyDto(company);
			dtos.add(companyDto);
		}

		return new ResponseEntity<List<CompanyDto>>(dtos, HttpStatus.OK);
	}

	@PostMapping("/filter")
	public ResponseEntity<List<CompanyDto>> filter(@RequestBody CompanySearchDto searchDto) {

		List<Company> companies = companyService.filterBy(searchDto);

		ArrayList<CompanyDto> dtos = new ArrayList<CompanyDto>();

		for (Company company : companies) {
			CompanyDto companyDto = new CompanyDto(company);
			dtos.add(companyDto);
		}

		return new ResponseEntity<List<CompanyDto>>(dtos, HttpStatus.OK);

	}

	@GetMapping("/{companyId}")
	public ResponseEntity<CompanyDto> findByCompanyId(@PathVariable("companyId") Long companyId) {

		Company comp = companyService.getById(companyId);

		if (comp == null) {
			return (ResponseEntity<CompanyDto>) ResponseEntity.badRequest();
		}
		CompanyDto dtos = new CompanyDto(comp);

		return new ResponseEntity<CompanyDto>(dtos, HttpStatus.OK);
	}

	@GetMapping("/incomeForDateRangeAdminsCompany")
	public ResponseEntity<ArrayList<ReservationDto>> getReservationsWithScheduledStatusForCompany(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			Principal principal) {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/numberOfReservationsLast3MonthsStatistics")
	public ResponseEntity<ArrayList<ReservationDto>> getNumberOfReservations(Principal principal) {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/numberOfReservationsYearStatistics")
	public ResponseEntity<ArrayList<ReservationDto>> getNumberOfReservationsYearStatistics(Principal principal) {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/equipmentStatistics")
	public ResponseEntity<List<EquipmentStatisticsDto>> getEquipmentStatistics(Principal principal) {

		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
		
		List<EquipmentStatisticsDto> dtos = companyService.getEquipmentStatistics(loggedUser);
		
		return new ResponseEntity<List<EquipmentStatisticsDto>>(dtos, HttpStatus.OK);
	}

}