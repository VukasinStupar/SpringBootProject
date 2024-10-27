package rs.ac.uns.ftn.springsecurityexample.dto;

import java.time.LocalDateTime;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;

public class AppointmentDto {
	private long id;
	private LocalDateTime dateAndTime;
	private int duration;
	private String adminName;
	private String companyName;
	
	public AppointmentDto() {
		super();
	}

	public AppointmentDto(Appointment appointment) {
		id = appointment.getId();
		dateAndTime = appointment.getDateAndTime();
		duration = appointment.getDuration();
		adminName = appointment.getAdministrator().getFirstName() + " " + appointment.getAdministrator().getLastName();
		companyName = appointment.getCompany().getName();
	}
	
	public Appointment mapToAppointment() {
		Appointment appointment= new Appointment();
	
		appointment.setDateAndTime(dateAndTime);
		appointment.setDuration(duration);
		return appointment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
	
}
