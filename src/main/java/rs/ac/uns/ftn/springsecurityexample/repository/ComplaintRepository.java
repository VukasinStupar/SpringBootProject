package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findAllByCompanyId(Long companyId);
    
    @Query("SELECT c FROM Complaint c WHERE c.company.id = :companyId")
    List<Complaint> findAllByCompanyId2Nacin(Long companyId);
    
    @Query("SELECT c FROM Complaint c WHERE c.response = NULL")
    List<Complaint> findAllNotResponded();
    
    @Query("SELECT c FROM Complaint c WHERE c.guest.id = :guestId")
    List<Complaint> findAllComplaintByUserId(@Param("guestId") Long guestId);
    
    @Query("SELECT c FROM Complaint c WHERE c.companyAdmin.id = :companyAdminId")
    List<Complaint> findAllComplaintByCompanyAdminId(@Param("companyAdminId") Long companyAdminId);
    
    List<Complaint> findAll();
    
}
