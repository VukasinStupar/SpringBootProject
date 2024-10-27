package rs.ac.uns.ftn.springsecurityexample.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import rs.ac.uns.ftn.springsecurityexample.dto.MonthIncomeDto;
import rs.ac.uns.ftn.springsecurityexample.dto.MonthIncomeForEachProduct;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationCountDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationDto;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.ReservationEquipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface ReservationService {

	Reservation createReservation(ReservationDto dto, User loggedUser);

	Reservation cancelReservation(long reservationId);

	List<Reservation> futureReservationsForUser(User loggedUser);

	Reservation takeReservation(Long id);
	
	List<Reservation> findReservationWithStatusScheduled();
	
	List<Reservation> findScehuledReservationsForAdmin(User loggedUser);
	
	List<Reservation> findAll();
	
	List<Reservation> findReservationsByAppointmentCompanyId(Long companyId);
	
	Reservation findReservationById( Long reservationId);
	
	List<Equipment> findEquipmentByReservationId(Long id);

	Reservation findReservationScheduledCompanyIdUserId(Long userId, Long companyId);
	
	List<MonthIncomeDto> getMonthIncomes();
	
	List<ReservationCountDto> getReservationCountForYear();
	
	
	List<MonthIncomeForEachProduct> getMonthIncomeForEachProduct();
}