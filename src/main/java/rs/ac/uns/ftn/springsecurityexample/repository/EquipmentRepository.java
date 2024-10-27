package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.ReservationEquipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByCompanyId(Long companyId);

    @Query("SELECT e FROM Equipment e WHERE e.company.id = :companyId")
    ArrayList<Equipment> findAllByCompanyId(@Param("companyId") Long companyId);
    
    
    
    @Query("SELECT e FROM Equipment e WHERE "
	         + "(:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND "
	         + "(:description IS NULL OR LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%')))")
    List<Equipment> searchEquipment(@Param("name") String name, @Param("description") String description);
    
   
    
 
}
