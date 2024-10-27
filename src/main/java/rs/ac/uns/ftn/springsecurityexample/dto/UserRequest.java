package rs.ac.uns.ftn.springsecurityexample.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;

import rs.ac.uns.ftn.springsecurityexample.model.User;

// DTO koji preuzima podatke iz HTML forme za registraciju
public class UserRequest {

	private Long id;

	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String email;

	private boolean enabled;

	private String city;

	private String country;

	private String phoneNumber;

	private String profession;

	private String informationAboutCompany;

	private Timestamp lastPasswordResetDate;

	public UserRequest() {

	}

	public UserRequest(User user) {
		id = user.getId();
		username = user.getUsername();
		password = user.getPassword();
		firstname = user.getFirstName();
		lastname = user.getLastName();
		email = user.getEmail();
		enabled = user.isEnabled();
		lastPasswordResetDate = user.getLastPasswordResetDate();
		city = user.getCity();
		country = user.getCountry();
		phoneNumber = user.getPhoneNumber();
		profession = user.getProfession();
		informationAboutCompany = user.getInformationAboutCompany();
		lastPasswordResetDate = user.getLastPasswordResetDate();
	}

	public User mapToUser(User user) {
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setEnabled(enabled);
		user.setLastPasswordResetDate(lastPasswordResetDate);
		user.setCity(city);
		user.setCountry(country);
		user.setPhoneNumber(phoneNumber);
		user.setProfession(profession);
		user.setInformationAboutCompany(informationAboutCompany);
		user.setLastPasswordResetDate(lastPasswordResetDate);

		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getInformationAboutCompany() {
		return informationAboutCompany;
	}

	public void setInformationAboutCompany(String informationAboutCompany) {
		this.informationAboutCompany = informationAboutCompany;
	}

}
