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

import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationDto;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.security.auth.TokenBasedAuthentication;
import rs.ac.uns.ftn.springsecurityexample.service.ComplaintService;

@RestController
@RequestMapping(value = "api/complaint")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ComplaintDto> saveComplaint(@RequestBody ComplaintDto complaintDto, Principal principal) {

		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
		Complaint complaint = complaintService.create(complaintDto, loggedUser);

		if(complaint == null) {
			return new ResponseEntity<ComplaintDto>( HttpStatus.BAD_REQUEST);

		}
		ComplaintDto createdDto = new ComplaintDto(complaint);
		return new ResponseEntity<ComplaintDto>(createdDto, HttpStatus.CREATED);
	}

	@GetMapping("/byCompany/{companyId}")
	public ResponseEntity<List<ComplaintDto>> findAllComplaintByCompanyId(@PathVariable("companyId") Long companyId) {

		List<Complaint> complaints = complaintService.getByCompanyId(companyId);
		List<ComplaintDto> dtos = new ArrayList<ComplaintDto>();

		for (Complaint complaint : complaints) {
			dtos.add(new ComplaintDto(complaint));
		}

		return new ResponseEntity<List<ComplaintDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/getAllNotResponded}")
	public ResponseEntity<List<ComplaintDto>> findAllComplaintByCompanyId() {

		List<Complaint> complaints = complaintService.findAllNotResponded();
		List<ComplaintDto> dtos = new ArrayList<ComplaintDto>();

		for (Complaint complaint : complaints) {
			dtos.add(new ComplaintDto(complaint));
		}

		return new ResponseEntity<List<ComplaintDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/getNotResponded}")
	public ResponseEntity<List<ComplaintDto>> getNotResponded() {

		List<Complaint> complaints = complaintService.findAllNotResponded();
		List<ComplaintDto> dtos = new ArrayList<ComplaintDto>();

		for (Complaint complaint : complaints) {
			dtos.add(new ComplaintDto(complaint));
		}

		return new ResponseEntity<List<ComplaintDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/userComplaintHistory")
	public ResponseEntity<List<ComplaintDto>> complaintUserIdSetResponse(Principal principal) {
		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();

		Long userId = loggedUser.getId();
		List<Complaint> complaints = complaintService.findAllComplaintByUserId(userId);

		List<ComplaintDto> dtos = new ArrayList<ComplaintDto>();

		for (Complaint complaint : complaints) {
			ComplaintDto compDto = new ComplaintDto(complaint);
			dtos.add(compDto);
		}

		return new ResponseEntity<List<ComplaintDto>>(dtos, HttpStatus.OK);

	}

	@PutMapping("/respondeComplaint/{id}")
	public ResponseEntity<ComplaintDto> complaintUserIdSetResponse(@RequestBody ComplaintDto complaintDto) {

		Complaint complaint = complaintService.respondeComplaint(complaintDto);

		ComplaintDto complaintDtos = new ComplaintDto(complaint);

		return new ResponseEntity<ComplaintDto>(complaintDtos, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<ComplaintDto>> findAllComplaint() {
		List<Complaint> complaints = complaintService.findAll();
		if (complaints == null) {
			return null;
		}

		List<ComplaintDto> dtos = new ArrayList<ComplaintDto>();

		for (Complaint complaint : complaints) {
			dtos.add(new ComplaintDto(complaint));
		}

		return new ResponseEntity<List<ComplaintDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComplaintDto> findById(@PathVariable("id") Long id) {
		Complaint complaint = complaintService.findById(id);
		if (complaint == null) {
			return new ResponseEntity<ComplaintDto>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ComplaintDto>(new ComplaintDto(complaint), HttpStatus.OK);
	}

	@GetMapping("/byCompanyAdminId/{id}")
	public ResponseEntity<List<ComplaintDto>> findAllComplaintByCompanyAdminId(
			@PathVariable("id") Long companyAdminId) {
		List<Complaint> complaints = complaintService.findAllComplaintByCompanyAdminId(companyAdminId);
		if (complaints == null) {
			return null;
		}

		List<ComplaintDto> dtos = new ArrayList<ComplaintDto>();

		for (Complaint complaint : complaints) {
			dtos.add(new ComplaintDto(complaint));
		}

		return new ResponseEntity<List<ComplaintDto>>(dtos, HttpStatus.OK);
	}

}
