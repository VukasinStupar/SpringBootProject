package com.example.model;

import java.util.Scanner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest_id", nullable = false)
	private User guest;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_admin_id", nullable = true)
	private User companyAdmin;

	@Column(name = "text", nullable = false)
	private String text;

	@Column(name = "response", nullable = true)
	private String response;

	public Complaint() {
		super();
	}

	public Complaint(Integer id, User guest, Company company, User companyAdmin, String text, String response) {
		super();
		this.id = id;
		this.guest = guest;
		this.company = company;
		this.companyAdmin = companyAdmin;
		this.text = text;
		this.response = response;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getCompanyAdmin() {
		return companyAdmin;
	}

	public void setCompanyAdmin(User companyAdmin) {
		this.companyAdmin = companyAdmin;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}