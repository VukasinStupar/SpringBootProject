package rs.ac.uns.ftn.springsecurityexample.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationCountDto {
	private Integer reservationCount;
	private String month;

	public ReservationCountDto() {
		super();
	}

	public ReservationCountDto(Integer reservationCount, String month) {
		super();
		this.reservationCount = reservationCount;
		this.month = month;
	}

	public ReservationCountDto(Integer reservationCount, LocalDate month) {
		super();
		this.reservationCount = reservationCount;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		this.month = month.format(formatter);
	}

	public Integer getReservationCount() {
		return reservationCount;
	}

	public void setReservationCount(Integer reservationCount) {
		this.reservationCount = reservationCount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
