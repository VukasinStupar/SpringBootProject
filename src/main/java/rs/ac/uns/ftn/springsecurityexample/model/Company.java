package rs.ac.uns.ftn.springsecurityexample.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "adress", nullable = false)
	private String adress;

	@Column(name = "description", nullable = false)
	private String description; 

	@Column(name = "average_grade")
	private float averageGrade;

	@Column(name = "works_from")
	private LocalTime worksFrom;

	@Column(name = "works_to")
	private LocalTime worksTo;

	public Company() {
		super();
	}

	public Company(long id, String name, String adress, String description, float averageGrade) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.averageGrade = averageGrade;
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

	public LocalTime getWorksFrom() {
		return worksFrom;
	}

	public void setWorksFrom(LocalTime worksFrom) {
		this.worksFrom = worksFrom;
	}

	public LocalTime getWorksTo() {
		return worksTo;
	}

	public void setWorksTo(LocalTime worksTo) {
		this.worksTo = worksTo;
	}
	
	

}