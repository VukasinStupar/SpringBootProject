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
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "grade_value", nullable = false)
	private int gradeValue;

	@Column(name = "reason", nullable = false)
	private String reason;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest_id", nullable = false)
	private User guest;

	public Grade() {
		super();
	}

	public Grade(Long id, int gradeValue, String reason, Company company, User guest) {
		super();
		this.id = id;
		this.gradeValue = gradeValue;
		this.reason = reason;
		this.company = company;
		this.guest = guest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(int gradeValue) {
		this.gradeValue = gradeValue;
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
