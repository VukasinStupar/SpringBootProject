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

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ExtraordinaryAppointmentDto;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.security.auth.TokenBasedAuthentication;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;

//TODO: autorizacija
@RestController
@RequestMapping(value = "api/appointments") 
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

	@PostMapping(consumes = "application/json")
    public ResponseEntity<AppointmentDto> saveAppointment(@RequestBody AppointmentDto appointmentDto,
            Principal principal) {
        User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal(); 

        Appointment appointment = appointmentService.create(appointmentDto, loggedUser);
        if(appointment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        AppointmentDto createDto = new AppointmentDto(appointment);

        return new ResponseEntity<AppointmentDto>(createDto, HttpStatus.CREATED);
    }

	
	@GetMapping("/byCompanyId/{id}")
	public ResponseEntity<ArrayList<AppointmentDto>> byCompanyId(@PathVariable("id") Long id) {
		ArrayList<Appointment> appointments = (ArrayList<Appointment>) appointmentService.getAllByCompanyId(id);

		ArrayList<AppointmentDto> dtos = new ArrayList<AppointmentDto>();
		for (Appointment appointment : appointments) {
			dtos.add(new AppointmentDto(appointment));
		}
		return new ResponseEntity<ArrayList<AppointmentDto>>(dtos, HttpStatus.OK);

	}

	@GetMapping("/byCompanyIdAvailable/{id}")
	public ResponseEntity<ArrayList<AppointmentDto>> byCompanyIdGuestIdNull(@PathVariable("id") Long id) {
		List<Appointment> appointments = appointmentService.findAllByCompanyIdAvailable(id);

		ArrayList<AppointmentDto> dtos = new ArrayList<AppointmentDto>();
		for (Appointment appointment : appointments) {
			dtos.add(new AppointmentDto(appointment));
		}
		return new ResponseEntity<ArrayList<AppointmentDto>>(dtos, HttpStatus.OK);
	}

	@PostMapping("/createExtraordinaryAppointment")
	public ResponseEntity<AppointmentDto> createExtraordinaryAppointment(
			@RequestBody ExtraordinaryAppointmentDto extraordinaryAppointmentDto, Principal principal) {
		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

		Appointment appointment = appointmentService.createExtraordinaryAppointment(extraordinaryAppointmentDto,
				loggedUser);
		AppointmentDto createDto = new AppointmentDto(appointment);

		return new ResponseEntity<AppointmentDto>(createDto, HttpStatus.CREATED);
	}

	@GetMapping("/findFreeExtraordinaryAppointments")
	public ResponseEntity<List<ExtraordinaryAppointmentDto>> findExtraordinaryAppointmentsForDate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam("companyId") long companyId, Principal principal) {
		ArrayList<ExtraordinaryAppointmentDto> dtos = appointmentService.findExtraordinaryAppointmentsForDate(date,
				companyId);

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<AppointmentDto>> findAllAppointments() {
		List<Appointment> appointments = appointmentService.findAll();
		if(appointments == null) {
			return null;
		}
		
		ArrayList<AppointmentDto> dtos = new ArrayList<AppointmentDto>();
		
		for(Appointment iter: appointments) {
			dtos.add(new AppointmentDto(iter));
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
}