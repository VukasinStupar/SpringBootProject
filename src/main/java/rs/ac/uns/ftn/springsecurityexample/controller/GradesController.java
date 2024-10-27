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
import rs.ac.uns.ftn.springsecurityexample.dto.GradeDto;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.Grade;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.security.auth.TokenBasedAuthentication;
import rs.ac.uns.ftn.springsecurityexample.service.GradeService;

@RestController
@RequestMapping(value = "api/grades")
public class GradesController {

	@Autowired
	private GradeService gradeService;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<GradeDto> createGrade(@RequestBody GradeDto gradeDto, Principal principal) {

		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
		if (loggedUser == null) {
			return new ResponseEntity<GradeDto>(HttpStatus.NOT_FOUND);
		}

		Long userId = loggedUser.getId();

		Grade grade = gradeService.create(gradeDto, userId);
		if (grade == null) {
			return new ResponseEntity<GradeDto>(HttpStatus.BAD_REQUEST);
		}

		GradeDto createDto = new GradeDto(grade);

		return new ResponseEntity<GradeDto>(createDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GradeDto> complaintUserIdSetResponse(@RequestBody GradeDto gradeDto) {

		Grade grade = gradeService.update(gradeDto);
		if (grade == null) {
			return new ResponseEntity<GradeDto>(HttpStatus.BAD_REQUEST);
		}

		GradeDto createDto = new GradeDto(grade);

		return new ResponseEntity<GradeDto>(createDto, HttpStatus.OK);

	}
	

	@GetMapping("/allGradesForUser")
	public ResponseEntity<List<GradeDto>> findGradeUserId(Principal principal) {
		User loggedUser = (User) ((TokenBasedAuthentication) principal).getPrincipal();
		if (loggedUser == null) {
			return new ResponseEntity<List<GradeDto>>(HttpStatus.NOT_FOUND);
		}
		Long userId = loggedUser.getId();

		List<Grade> grades = gradeService.findGradeUserId(userId);
		if (grades == null) {
			return new ResponseEntity<List<GradeDto>>(HttpStatus.BAD_REQUEST);
		}
		ArrayList<GradeDto> dtos = new ArrayList<GradeDto>();
		for (Grade g : grades) {
			GradeDto gradeDto = new GradeDto(g);
			dtos.add(gradeDto);
		}

		return new ResponseEntity<List<GradeDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GradeDto> getById(@PathVariable("id")Long id) {

		Grade grade = gradeService.findGradeById(id);
		if(grade == null) {
			return new ResponseEntity<GradeDto>(HttpStatus.BAD_REQUEST); 
		}

		GradeDto gradeDto = new GradeDto(grade);

		return new ResponseEntity<GradeDto>(gradeDto, HttpStatus.OK);
	}
	
	

}
