package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface CompanyRepository extends JpaRepository<Company, Long> {


	@Query("SELECT c FROM Company c WHERE "
	         + "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND "
	         + "(:adress IS NULL OR LOWER(c.adress) LIKE LOWER(CONCAT('%', :adress, '%'))) AND "
	         + "(:gradeFrom IS NULL OR c.averageGrade >= :gradeFrom) AND "
	         + "(:gradeTo IS NULL OR c.averageGrade <= :gradeTo)")
	    List<Company> searchCompanies(@Param("name") String name, 
	                                  @Param("adress") String adress, 
	                                  @Param("gradeFrom") Float gradeFrom, 
	                                  @Param("gradeTo") Float gradeTo);
	
	
	
	
			
	
}

