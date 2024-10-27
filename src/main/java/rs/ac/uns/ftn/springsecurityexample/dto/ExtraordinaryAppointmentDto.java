package rs.ac.uns.ftn.springsecurityexample.dto;

import java.time.LocalDateTime;

public class ExtraordinaryAppointmentDto {
	private LocalDateTime dateAndTime;
	private int duration;
	private long companyId;
	// koji equipment je korisnik selekotvao

	public ExtraordinaryAppointmentDto() {
		super();
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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

}
