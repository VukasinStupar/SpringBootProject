package rs.ac.uns.ftn.springsecurityexample.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByCompanyId(Long companyId);

	@Query("SELECT a FROM Appointment a WHERE a.company.id = :companyId AND a.status = 'FREE'")
	List<Appointment> findAllByCompanyIdAvailable(@Param("companyId") Long companyId);

	@Query("SELECT a FROM Appointment a WHERE a.company.id = :companyId AND a.dateAndTime = :dateTime")
	List<Appointment> findAllByCompanyIdAndDateTime(@Param("companyId") Long companyId,
			@Param("dateTime") LocalDateTime dateTime);

	//boolean existsByCompanyIdAndDateAndTime(Long companyId, LocalDateTime dateAndTime);
	
	 @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END " +
	           "FROM Appointment a " +
	           "WHERE a.company.id = :companyId " +
	           "AND a.dateAndTime = :dateAndTime")
	    boolean existsByCompanyIdAndDateAndTime(@Param("companyId") Long companyId, 
	                                            @Param("dateAndTime") LocalDateTime dateAndTime);
	
	@Query("SELECT a FROM Appointment a WHERE a.dateAndTime = :date_and_time AND a.administrator.id = :administrator_id")
	Appointment findOneAppointmentByAdminAndDateTime(@Param("date_and_time") LocalDateTime dateAndTime, @Param("administrator_id") Long administrator_id);
	
	List<Appointment> findAll();
}
