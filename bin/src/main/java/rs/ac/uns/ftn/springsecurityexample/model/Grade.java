package com.example.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "grade", nullable= false)
	private int grade;
	
	@Column(name = "reason", nullable= false)
	private String reason;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "guest_id", nullable=false)
	private User guest;

	public Grade() {
		super();
	}

	public Grade(Integer id, int grade, String reason, Company company, User guest) {
		super();
		this.id = id;
		this.grade = grade;
		this.reason = reason;
		this.company = company;
		this.guest = guest;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	
	
}
