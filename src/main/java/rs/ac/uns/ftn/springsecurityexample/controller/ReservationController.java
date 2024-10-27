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
import rs.ac.uns.ftn.springsecurityexample.dto.MonthIncomeDto;
import rs.ac.uns.ftn.springsecurityexample.dto.MonthIncomeForEachProduct;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationCountDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationDto;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.security.auth.TokenBasedAuthentication;
import rs.ac.uns.ftn.springsecurityexample.service.ReservationService;

@RestController
@RequestMapping(value = "api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto,
			Principal principal) {
		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

		Reservation reservedAppointmet = reservationService.createReservation(reservationDto, loggedUser);

		if (reservedAppointmet == null) {
			return new ResponseEntity<ReservationDto>(HttpStatus.BAD_REQUEST);
		}
		ReservationDto dto = new ReservationDto(reservedAppointmet);

		return new ResponseEntity<ReservationDto>(dto, HttpStatus.OK);

	}

	@PutMapping("/cancelReservation/{id}")
	public ResponseEntity<ReservationDto> cancelReservation(@PathVariable("id") Long id) {

		Reservation cancelReservation = reservationService.cancelReservation(id);
		if (cancelReservation == null) {
			return new ResponseEntity<ReservationDto>(HttpStatus.BAD_REQUEST);

		}
		ReservationDto reservationDto = new ReservationDto(cancelReservation);
		return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.OK);
	}

	@GetMapping("/futureReservations")
	public ResponseEntity<ArrayList<ReservationDto>> futureReservations(Principal principal) {

		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

		List<Reservation> reservations = reservationService.futureReservationsForUser(loggedUser);

		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();
		for (Reservation reservation : reservations) {
			dtos.add(new ReservationDto(reservation));
		}
		return new ResponseEntity<ArrayList<ReservationDto>>(dtos, HttpStatus.OK);

	}

	@GetMapping("/byReservationId/{id}")
	public ResponseEntity<ReservationDto> findByReservationId(@PathVariable("id") Long id) {
		Reservation reservation = reservationService.findReservationById(id);
		if (reservation == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		ReservationDto reservationDto = new ReservationDto(reservation);

		return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.CREATED);
	}

	@GetMapping("/reservationStatusScheduled")
	public ResponseEntity<ArrayList<ReservationDto>> getReservationsWithScheduledStatus() {
		List<Reservation> reservation = reservationService.findReservationWithStatusScheduled();

		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();

		for (Reservation reserv : reservation) {
			dtos.add(new ReservationDto(reserv));
		}
		return new ResponseEntity<ArrayList<ReservationDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/scheduledReservationsForAdminsCompany")
	public ResponseEntity<ArrayList<ReservationDto>> getReservationsWithScheduledStatusForCompany(Principal principal) {
		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
		List<Reservation> reservation = reservationService.findScehuledReservationsForAdmin(loggedUser);
		if (reservation == null) {
			return new ResponseEntity<ArrayList<ReservationDto>>(HttpStatus.BAD_REQUEST);
		}
		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();

		for (Reservation reserv : reservation) {
			dtos.add(new ReservationDto(reserv));
		}
		return new ResponseEntity<ArrayList<ReservationDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ArrayList<ReservationDto>> findAllReservations() {
		List<Reservation> reservation = reservationService.findAll();

		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();

		for (Reservation reserv : reservation) {
			dtos.add(new ReservationDto(reserv));
		}
		return new ResponseEntity<ArrayList<ReservationDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/reservationsByAppointmentCompanyId/{id}")
	public ResponseEntity<ArrayList<ReservationDto>> getReservationsByAppointmentCompanyId(
			@PathVariable("id") Long id) {
		List<Reservation> reservation = reservationService.findReservationsByAppointmentCompanyId(id);
		if (reservation == null) {
			return null;
		}

		ArrayList<ReservationDto> dtos = new ArrayList<ReservationDto>();

		for (Reservation reserv : reservation) {
			dtos.add(new ReservationDto(reserv));
		}
		return new ResponseEntity<ArrayList<ReservationDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReservationDto> findReservationById(@PathVariable("id") Long id) {
		Reservation reservation = reservationService.findReservationById(id);

		if (reservation == null) {
			return new ResponseEntity<ReservationDto>(HttpStatus.BAD_REQUEST);
		}
		ReservationDto reservationDto = new ReservationDto(reservation);
		return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.OK);
	}

	@GetMapping("/equipmentByReservationId/{id}")
	public ResponseEntity<List<EquipmentDto>> listOfEquipemntsByIdTakedByEqipmentReservation(@PathVariable Long id) {
		List<Equipment> equipments = reservationService.findEquipmentByReservationId(id);

		List<EquipmentDto> dtos = new ArrayList<EquipmentDto>();

		for (Equipment equip : equipments) {
			dtos.add(new EquipmentDto(equip));
		}

		return new ResponseEntity<List<EquipmentDto>>(dtos, HttpStatus.OK);

	}

	@PutMapping("/takeReservation/{id}")
	public ResponseEntity<ReservationDto> takeReservation(@PathVariable("id") Long id) {
		Reservation reservation = reservationService.takeReservation(id);
		if (reservation == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		ReservationDto reservationDto = new ReservationDto(reservation);

		return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/reservationScheduledCompanyIdUserId/{companyId}")
	public boolean findReservationScheduledCompanyIdUserId(Principal principal, @PathVariable("companyId") Long companyId) {
		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
		
		Long userId= loggedUser.getId();
		
		Reservation reservation = reservationService.findReservationScheduledCompanyIdUserId(userId, companyId);
		
		if(reservation == null) {
			return false;
		}
		return true;
		
	}
	
	@GetMapping("/getMonthIncomes")
	public List<MonthIncomeDto> getMonthIncomes(MonthIncomeDto MonthIncomeDto) {
		
		List<MonthIncomeDto> monthIncomeDto  = reservationService.getMonthIncomes();
		
		return monthIncomeDto;
		
	}
	
	@GetMapping("/getMonthsEarning")
	public List<MonthIncomeDto> getMonthsEarning(MonthIncomeDto MonthIncomeDto) {
		
		List<MonthIncomeDto> monthIncomeDto  = reservationService.getMonthIncomes();
		
		
		return monthIncomeDto;
		
	}
	
	@GetMapping("/getReservationCountForYear")
	public List<ReservationCountDto> getReservationCountForYear(ReservationCountDto reservationCountDto) {
		
		List<ReservationCountDto> dto  = reservationService.getReservationCountForYear();
		
		
		return dto;
		
	}
	
	@GetMapping("/getMonthIncomeForEachEquipment")
	public List<MonthIncomeForEachProduct> getMonthIncomeForEachEquipment(ReservationCountDto reservationCountDto) {
		
		List<MonthIncomeForEachProduct> dto  = reservationService.getMonthIncomeForEachProduct();
		
		
		return dto;
		
	}
	
	

}
