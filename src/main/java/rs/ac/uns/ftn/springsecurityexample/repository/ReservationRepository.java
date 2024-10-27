package rs.ac.uns.ftn.springsecurityexample.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import rs.ac.uns.ftn.springsecurityexample.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	@Query("SELECT r FROM Reservation r WHERE r.user.id = :userId AND r.appointment.dateAndTime > :reservationTime")
	List<Reservation> findFutureReservations(@Param("userId") Long userId, @Param("reservationTime") LocalDateTime reservationTime);

	@Query("SELECT r FROM Reservation r WHERE r.user.id = :userId AND r.appointment.id = :appointmentId")
	Reservation findReservationByUserAndAppointment(@Param("userId") Long userId, @Param("appointmentId") Long appointmentId);
	
	@Query("SELECT r FROM Reservation r WHERE r.status = 'SCHEDULED' ")
	List<Reservation> findReservationWithStatusScheduled();
	
	@Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.status = 'SCHEDULED' AND r.appointment.id = :appointmentId")
	boolean existsReservationWithStatusScheduledAndAppointmentId(@Param("appointmentId") Long appointmentId);

	@Query("SELECT r FROM Reservation r WHERE r.status = 'SCHEDULED' AND r.appointment.company.id = :companyId")
	List<Reservation> findScheduledReservationsForCompany(@Param("companyId") Long companyId);
	
	@Query("SELECT r FROM Reservation r WHERE r.user.id = :userId AND r.appointment.company.id = :companyId")
	List<Reservation> findReservationCompanyAndUserId(@Param("userId") Long userId, @Param("companyId") Long companyId);
	
	@Query("SELECT r FROM Reservation r WHERE r.appointment.administrator.id = :adminId AND r.user.id = :userId")
	List<Reservation> findUserReservationsForCompany(@Param("adminId") Long adminId, @Param("userId") Long userId);
	
	List<Reservation> findAll();
	
	@Query("SELECT r FROM Reservation r WHERE r.appointment.company.id = :companyId")
	List<Reservation> findReservationsByAppointmentCompanyId(@Param("companyId") Long companyId);
	
	@Query("SELECT r FROM Reservation r WHERE r.status = 'SCHEDULED' and r.appointment.company.id = :companyId and r.user.id = :userId")
	Reservation findReservationScheduledCompanyIdUserId(@Param("companyId") Long companyId, @Param("userId") Long userId);
	
	@Query("SELECT SUM(r.totalPrice) FROM Reservation r WHERE r.appointment.dateAndTime BETWEEN :startDate AND :endDate")
    Integer sumTotalPriceByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	
	@Query("SELECT COUNT(r) FROM Reservation r WHERE r.appointment.dateAndTime BETWEEN :startDate AND :endDate")
	Integer countReservationsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

	
	

	
	

}