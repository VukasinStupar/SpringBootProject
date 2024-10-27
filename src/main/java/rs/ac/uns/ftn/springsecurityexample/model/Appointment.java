package rs.ac.uns.ftn.springsecurityexample.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.model.enums.ReservationStatus;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date_and_time", nullable = false)
	private LocalDateTime dateAndTime;

	@Column(name = "duration", nullable = false)
	private int duration;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrator_id", nullable = false)
	private User administrator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	public Appointment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public User getAdministrator() {
		return administrator;
	}

	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	// modelujemo koji korisnik je zauzeo taj termin i koji sve appointment je
	// odabrao

}
