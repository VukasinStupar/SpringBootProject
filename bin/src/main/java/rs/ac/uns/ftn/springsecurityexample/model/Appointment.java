package com.example.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date_and_time", nullable = false)
	private LocalDateTime dateAndTime;
	
	@Column(name = "duration", nullable = false)
	private int duration;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrator_id", nullable = false)
	private User administrator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guest_id", nullable = true)
	private User guest;
	
	public Appointment() {
		super();
	}

	public Appointment(Integer id, LocalDateTime dateAndTime, int duration, User administrator, User guest) {
		super();
		this.id = id;
		this.dateAndTime = dateAndTime;
		this.duration = duration;
		this.administrator = administrator;
		this.guest = guest;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	// modelujemo koji korisnik je zauzeo taj termin i koji sve appointment je
	// odabrao

}
