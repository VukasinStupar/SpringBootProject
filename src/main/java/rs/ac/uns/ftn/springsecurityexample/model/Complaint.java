package rs.ac.uns.ftn.springsecurityexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest_id", nullable = false)
	private User guest;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_admin_id", nullable = true)
	private User companyAdmin;

	@Column(name = "complaint_text", nullable = false)
	private String text;

	@Column(name = "response", nullable = true)
	private String response;			//null ako nema odogovor
	

	public Complaint() {
		super();
	}

	public Complaint(Long id, User guest, Company company, User companyAdmin, String text, String response) {
		super();
		this.id = id;
		this.guest = guest;
		this.company = company;
		this.companyAdmin = companyAdmin;
		this.text = text;
		this.response = response;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}