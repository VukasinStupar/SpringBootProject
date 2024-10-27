package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.Company;

public class CompanyDto {
	private Long id;
	private String name;
	private String adress;
	private String description;
	private float averageGrade;

	public CompanyDto() {
		super();
	}

	public CompanyDto(Company company) {
		id = company.getId();
		name = company.getName();
		adress = company.getAdress();
		description = company.getDescription();
		averageGrade = company.getAverageGrade();
	}

	public Company mapToCompany() {
		Company company = new Company();
		company.setName(name);
		company.setAdress(adress);
		company.setDescription(description);
		company.setAverageGrade(averageGrade);
		return company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(float averageGrade) {
		this.averageGrade = averageGrade;
	}

}
