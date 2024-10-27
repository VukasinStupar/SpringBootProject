package rs.ac.uns.ftn.springsecurityexample.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.ReservationEquipment;

public interface ReservationEquipmentRepository extends JpaRepository<ReservationEquipment, Long>{
	
	@Query("SELECT re FROM ReservationEquipment re WHERE re.reservation.id = :reservation_id")
	ReservationEquipment findByReservation_id(@Param("reservation_id") Integer integer);
	
	@Query("SELECT re FROM ReservationEquipment re WHERE re.equipment.id = :equipmentId")
	List<ReservationEquipment> findReservationByEquipmentId(@Param("equipmentId") Long equipmentId);
	
	@Query("SELECT re FROM ReservationEquipment re WHERE re.reservation.id = :reservationId")
	List<ReservationEquipment> findEquipmentByReservationId(@Param("reservationId") Long reservationId);

	@Query("SELECT COUNT(re) FROM ReservationEquipment re WHERE re.equipment.id = :equipmentId")
	Long countReservationsByEquipmentId(@Param("equipmentId") Long equipmentId);
	
	@Query("SELECT COUNT(re) FROM ReservationEquipment re WHERE re.equipment.id = :equipmentId AND re.reservation.appointment.dateAndTime BETWEEN :startDate AND :endDate")
	Long countReservationsByEquipmentIdAndDateRange(@Param("equipmentId") Long equipmentId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	
	
	
}
