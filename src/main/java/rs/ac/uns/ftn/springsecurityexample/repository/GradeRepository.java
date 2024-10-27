package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {


	@Query("SELECT g FROM Grade g WHERE g.company.id = :companyId and g.guest.id = :guestId")
	Grade findGradeCompanyIdUserId(@Param("companyId") Long companyId, @Param("guestId") Long guestId);
	
	@Query("SELECT AVG(g.gradeValue) FROM Grade g WHERE g.company.id = :companyId")
    Float findAverageGradeByCompanyId(@Param("companyId") Long companyId);
	
	@Query("SELECT g FROM Grade g WHERE g.guest.id = :guestId")
	List<Grade> findGradeUserId(@Param("guestId") Long guestId);
	
	

}
