package rs.ac.uns.ftn.springsecurityexample.model;

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

import rs.ac.uns.ftn.springsecurityexample.model.enums.ReservationStatus;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "appointment_id", nullable = false)
	private Appointment appointment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@Column(name = "total_price", nullable = false)
	private int totalPrice;

	public Reservation() {
		super();
	}

	

	public Reservation(Long id, Appointment appointment, User user, ReservationStatus status, int totalPrice) {
		super();
		this.id = id;
		this.appointment = appointment;
		this.user = user;
		this.status = status;
		this.totalPrice = totalPrice;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Appointment getAppointment() {
		return appointment;
	}



	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public ReservationStatus getStatus() {
		return status;
	}



	public void setStatus(ReservationStatus status) {
		this.status = status;
	}



	public int getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}



}
