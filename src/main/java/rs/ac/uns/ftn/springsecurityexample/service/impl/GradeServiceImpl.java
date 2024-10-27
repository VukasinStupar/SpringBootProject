package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.GradeDto;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Grade;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.CompanyRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.GradeRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ReservationRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Grade create(GradeDto dto, Long userId) {
		Company company = companyRepository.findById(dto.getCompanyId()).orElse(null);
		if (company == null) {
			return null;
		}

		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			return null;
		}
		
		Reservation reservation = reservationRepository.findReservationScheduledCompanyIdUserId(userId, company.getId());
		if(reservation == null) {
			return null;
		}
		
		Grade checkGrade = gradeRepository.findGradeCompanyIdUserId(company.getId(), userId);
		if(checkGrade != null) {
			return null;
		}

		Grade grade = dto.mapToGrade();
		grade.setCompany(company);
		grade.setGuest(user);

		grade = gradeRepository.save(grade);
		
		Float newAverageGrade = gradeRepository.findAverageGradeByCompanyId(company.getId());
		company.setAverageGrade(newAverageGrade);
		companyRepository.save(company);
		
		return grade;
	}
	
	@Override
	public Grade update(GradeDto dto) {
		Grade grade = gradeRepository.findById(dto.getId()).orElse(null);
		if(grade == null) {
			return null;
		}
		Company company = companyRepository.findById(dto.getCompanyId()).orElse(null);
		if(company == null) {
			return null;
		}
		
		grade.setGradeValue(dto.getGradeValue());
		grade.setReason(dto.getReason());

		grade = gradeRepository.save(grade);
		
		
		Float newAverageGrade = gradeRepository.findAverageGradeByCompanyId(dto.getCompanyId());
		company.setAverageGrade(newAverageGrade);
		companyRepository.save(company);
		
		return grade;
	}

	@Override
	public List<Grade> findGradeUserId(Long guestId) {
		return gradeRepository.findGradeUserId(guestId);
	}
	
	@Override
	public Grade findGradeById(Long gradeId) {
		return gradeRepository.findById(gradeId).orElse(null);
	}

	
	

}
