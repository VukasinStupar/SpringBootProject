package rs.ac.uns.ftn.springsecurityexample.dto;

import java.time.LocalDateTime;
import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.enums.ReservationStatus;

public class ReservationDto {
	private Long id;
	private Long appointmentId;
	private String user;
	private ReservationStatus status;
	private List<Long> equipmentIds;

	private LocalDateTime dateAndTime;
	private int duration;
	private String companyName;
	
	private String adminFullName;
	private int totalPrice;

	public ReservationDto() {
		super();
	}

	public ReservationDto(Reservation reservation) {
		appointmentId = reservation.getAppointment().getId();
		user = reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName();
		status = reservation.getStatus();

		dateAndTime = reservation.getAppointment().getDateAndTime();
		duration = reservation.getAppointment().getDuration();
		companyName = reservation.getAppointment().getCompany().getName();

		id = reservation.getId();
		
		adminFullName = reservation.getAppointment().getAdministrator().getFirstName() + " "+reservation.getAppointment().getAdministrator().getLastName();
		totalPrice = reservation.getTotalPrice();
	}

	public Reservation mapToReservation() {
		Reservation reservation = new Reservation();
		reservation.getUser().setUsername(user);
		reservation.setStatus(status);
		return reservation;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public List<Long> getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(List<Long> equipmentIds) {
		this.equipmentIds = equipmentIds;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminFullName() {
		return adminFullName;
	}

	public void setAdminFullName(String adminFullName) {
		this.adminFullName = adminFullName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	

	

}
