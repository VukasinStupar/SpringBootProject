package rs.ac.uns.ftn.springsecurityexample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationDto;
import rs.ac.uns.ftn.springsecurityexample.dto.UserDto;
import rs.ac.uns.ftn.springsecurityexample.dto.UserRequest;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.service.UserService;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("user/{userId}")
	// @PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	// @GetMapping("/user/all")
//	@PreAuthorize("hasRole('ADMIN')")
//	public List<User> loadAll() {
	// return this.userService.findAll();
	// }

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<ArrayList<UserDto>> getAll() {
		List<User> user = userService.findAll();

		ArrayList<UserDto> dtos = new ArrayList<UserDto>();
		for (User us : user) {
			dtos.add(new UserDto(us));
		}
		return new ResponseEntity<ArrayList<UserDto>>(dtos, HttpStatus.OK);

	}

	@GetMapping("/getAllCompanyAdmins")
	public ResponseEntity<ArrayList<UserDto>> getCompanyAdmins() {
		List<User> users = userService.getUsersByRoleName("ROLE_COMPANY_ADMIN");

		ArrayList<UserDto> dtos = new ArrayList<UserDto>();
		for (User us : users) {
			dtos.add(new UserDto(us));
		}
		return new ResponseEntity<ArrayList<UserDto>>(dtos, HttpStatus.OK);

	}


	@GetMapping("/getLoggedUser")
	public ResponseEntity<UserDto> getLoggedUser(Principal user) {

		User loggedUser = this.userService.findByUsername(user.getName());
		UserDto userDto = new UserDto(loggedUser);

		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

	}

	@GetMapping("/whoami")
	@PreAuthorize("hasRole('USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}

	@GetMapping("/foo")
	public Map<String, String> getFoo() {
		Map<String, String> fooObj = new HashMap<>();
		fooObj.put("foo", "bar");
		return fooObj;
	}
}
