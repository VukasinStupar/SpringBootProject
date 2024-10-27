package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ExtraordinaryAppointmentDto;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.CompanyRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ReservationRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}


	@Override
	public Appointment create(AppointmentDto dto, User user) {
		if (user.getCompany() == null) {
			return null;
		}

		Appointment appointment = dto.mapToAppointment();
		appointment.setAdministrator(user);
		appointment.setCompany(user.getCompany());
		appointment.setStatus(AppointmentStatus.FREE);
		appointment = appointmentRepository.save(appointment);

		return appointment;
	}

	@Override
	public List<Appointment> getAllByCompanyId(Long id) {
		return appointmentRepository.findByCompanyId(id);
	}

	/*
	 //2 nacin ukoliko ne bi bila implementirana logika vec u reservation status
	@Override
	public List<Appointment> findAllByCompanyIdAvailable(long id) {
		List<Appointment> appointmens = appointmentRepository.findByCompanyId(id);
		List<Appointment> availableReservations = new ArrayList<Appointment>();
		
		for(Appointment appointment : appointmens) {
			if(!reservationRepository.existsReservationWithStatusScheduledAndAppointmentId(appointment.getId())) {
				availableReservations.add(appointment);
			}
		}
		
		return availableReservations;
	}
	*/

	@Override
	public List<Appointment> findAllByCompanyIdAvailable(long id) {
		List<Appointment> appointmens = appointmentRepository.findAllByCompanyIdAvailable(id);
		
		return appointmens;
	}

	public User findFreeCompanyAdmin(long companyId, LocalDateTime dateTime) {

		List<User> users = userRepository.findByCompanyId(companyId);

		for (User user : users) {

			Appointment appointment = appointmentRepository.findOneAppointmentByAdminAndDateTime(dateTime,
					user.getId());

			if (appointment == null) {
				return user;
			}
		}
		return null;

	}

	@Override
	public Appointment createExtraordinaryAppointment(ExtraordinaryAppointmentDto dto, User user) {
		Company company = companyRepository.findById(dto.getCompanyId()).orElse(null);
		if (company == null) {
			return null;
		}

		User admin = findFreeCompanyAdmin(company.getId(), dto.getDateAndTime());
		if (admin == null) {
			return null;
		}

		Appointment appointment = new Appointment();
		appointment.setAdministrator(admin);
		appointment.setCompany(company);
		appointment.setDateAndTime(dto.getDateAndTime());
		appointment.setDuration(dto.getDuration());
		appointment.setStatus(AppointmentStatus.FREE);
		appointmentRepository.save(appointment);

		return appointment;
	}

	public ArrayList<ExtraordinaryAppointmentDto> findExtraordinaryAppointmentsForDate(LocalDate date, long companyId) {
		Company company = companyRepository.findById(companyId).orElse(null);
		if (company == null) {
			return null;
		}

		ArrayList<ExtraordinaryAppointmentDto> dtos = new ArrayList<ExtraordinaryAppointmentDto>();

		LocalTime iterTime = company.getWorksFrom();
		while (iterTime.plusMinutes(30).isAfter(company.getWorksTo())) {
			LocalDateTime dateIter = date.atTime(iterTime); // spaja localDatw i localTime u localDateTime

			ExtraordinaryAppointmentDto extraordinaryAppointmentDto = new ExtraordinaryAppointmentDto();

			if (!appointmentRepository.existsByCompanyIdAndDateAndTime(companyId, dateIter)) {

				extraordinaryAppointmentDto.setCompanyId(companyId);
				extraordinaryAppointmentDto.setDateAndTime(dateIter);
				extraordinaryAppointmentDto.setDuration(30);

				dtos.add(extraordinaryAppointmentDto);
			}

			iterTime = iterTime.plusMinutes(30);

		}

		return dtos;
	}

	
}