package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.User;

public class UserDto {

	private Long id;

	private String username;

	private String firstName;

	private String lastName;

	private String email;	

	private String city;

	private String country;

	private String phoneNumber;

	private String profession;

	private String informationAboutCompany;

	private int penals;
	
	private String role;

	public UserDto() {
		super();
	}

	public UserDto(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.city = user.getCity();
		this.country = user.getCountry();
		this.phoneNumber = user.getPhoneNumber();
		this.profession = user.getProfession();
		this.informationAboutCompany = user.getInformationAboutCompany();
		this.penals = user.getPenals();
		if(user.getRoles().size() > 0) {
			this.role = user.getRoles().get(0).getName();			
		}
	}
	
	public User mapTo() {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setCity(city);
		user.setCountry(country);
		user.setPhoneNumber(phoneNumber);
		user.setProfession(profession);
		user.setInformationAboutCompany(informationAboutCompany);
		user.setPenals(penals);
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getPenals() {
		return penals;
	}

	public void setPenals(int penals) {
		this.penals = penals;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
