package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.ComplaintDto;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Complaint;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.CompanyRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ComplaintRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ReservationRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private CompanyRepository companyRepositiry;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Override
	public Complaint create(ComplaintDto complaintDto, User user) {
		Complaint complaint = complaintDto.mapToComplaint();
		complaint.setGuest(user);

		if (complaintDto.getCompanyId() == null && complaintDto.getCompanyAdminId() == null) {
			return null;
		}
		if (complaintDto.getCompanyId() == null) {
			User companyAdmin = userRepository.findById(complaintDto.getCompanyAdminId()).orElse(null);
			if (companyAdmin == null) {
				return null;
			}
			Long companyAdminId = complaintDto.getCompanyAdminId();

			List<Reservation> reservationsByAdmin = reservationRepository.findUserReservationsForCompany(companyAdminId,
					user.getId());
			if (reservationsByAdmin.size() == 0) {
				return null;
			}

			complaint.setCompanyAdmin(companyAdmin);
		} else {
			Company company = companyRepositiry.findById(complaintDto.getCompanyId()).orElse(null);
			if (company == null) {
				return null;
			}
			Long companyId = complaintDto.getCompanyId();

			Long userId = user.getId();

			List<Reservation> reservations = reservationRepository.findReservationCompanyAndUserId(userId, companyId);
			if (reservations.size() == 0) {
				return null;
			}
			complaint.setCompany(company);
		}

		complaintRepository.save(complaint);

		return complaint;
	}

	public List<Complaint> getByCompanyId(Long companyId) {
		return complaintRepository.findAllByCompanyId(companyId);

		/*
		 * 
		 * losa praksa
		 *
		 * List<Complaint> complaints = companyRepositiry.findAll(); List<Complaint>
		 * novaLista = new ArrayList<Complaint>(); for(Complaint complaint : complaints)
		 * { if(complaint.getCompany().getId() == companyId) { novaLista.add(complaint);
		 * } } return novaLista;
		 */
	}

	@Override
	public List<Complaint> findAllNotResponded() {
		return complaintRepository.findAllNotResponded();

	}

	// sve complaint-e za logovanog usera
	// odgovor na zalbu???

	@Override
	public List<Complaint> findAllComplaintByUserId( Long userId) {
		List<Complaint> complaints = complaintRepository.findAllComplaintByUserId(userId);
		return complaints;
//		for (Complaint complaint : complaints) {
//			User u = complaint.getGuest();
//			emailServiceImpl.sendActivationCode(u);
//
//			complaint.setResponse(complaintDto.getResponse());
//
//		}
//		return complaints;

	}
	
	public Complaint respondeComplaint(ComplaintDto complaintDto) {
		Complaint complaint = complaintRepository.findById(complaintDto.getId()).orElse(null);
		if(complaint == null) {
			return null;
		}
		complaint.setResponse(complaintDto.getResponse());
		complaintRepository.save(complaint);
		return complaint;
	}

	@Override
	public List<Complaint> findAll() {
		return complaintRepository.findAll();
	}
	

	@Override
	public Complaint findById(Long id) {
		return complaintRepository.findById(id).orElse(null);
	}

	@Override
	public List<Complaint> findAllComplaintByCompanyAdminId(Long companyAdminId) {
		return complaintRepository.findAllComplaintByCompanyAdminId(companyAdminId);
		
	}
}
