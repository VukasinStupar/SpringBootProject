package rs.ac.uns.ftn.springsecurityexample.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.dto.AppointmentDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ExtraordinaryAppointmentDto;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface AppointmentService {

	Appointment create(AppointmentDto dto, User user);

	List<Appointment> getAllByCompanyId(Long id);

	List<Appointment> findAllByCompanyIdAvailable(long id);


	Appointment createExtraordinaryAppointment(ExtraordinaryAppointmentDto extraordinaryAppointmentDto, User loggedUser);

	ArrayList<ExtraordinaryAppointmentDto> findExtraordinaryAppointmentsForDate(LocalDate date, long companyId);
	
	List<Appointment> findAll();
}
