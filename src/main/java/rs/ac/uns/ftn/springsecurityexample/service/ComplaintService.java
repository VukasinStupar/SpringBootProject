package rs.ac.uns.ftn.springsecurityexample.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDto;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface ComplaintService {

	Complaint create(ComplaintDto complaintDto, User user);
	
	List<Complaint> getByCompanyId(Long companyId);
	
	List<Complaint> findAllNotResponded();
	
	List<Complaint> findAllComplaintByUserId(Long guestId);
	
	Complaint respondeComplaint(ComplaintDto complaintDto);
    
    List<Complaint> findAll();
	Complaint findById(Long id) ;
    
    List<Complaint> findAllComplaintByCompanyAdminId(Long companyAdminId);


}
