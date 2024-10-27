package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.MonthIncomeDto;
import rs.ac.uns.ftn.springsecurityexample.dto.MonthIncomeForEachProduct;
import rs.ac.uns.ftn.springsecurityexample.dto.ProductIncome;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationCountDto;
import rs.ac.uns.ftn.springsecurityexample.dto.ReservationDto;
import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.ReservationEquipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.model.enums.AppointmentStatus;
import rs.ac.uns.ftn.springsecurityexample.model.enums.ReservationStatus;
import rs.ac.uns.ftn.springsecurityexample.repository.AppointmentRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.EquipmentRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ReservationEquipmentRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ReservationRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.UserRepository;
import rs.ac.uns.ftn.springsecurityexample.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Autowired
	private ReservationEquipmentRepository reservationEquipmentRepository;

	@Autowired
	private EmailServiceImpl emailService;

	// @Override
	// public List<Reservation> findEmptyReservation() {
	// return reservationRepository.findEmptyReservation();
	// }

	@Override
	public Reservation createReservation(ReservationDto dto, User loggedUser) {
		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId()).orElse(null);
		if (appointment == null) {
			return null;
		}
		if (appointment.getStatus() == AppointmentStatus.SCHEDULED) {
			return null;
		}

		Reservation reservationCheck = reservationRepository.findReservationByUserAndAppointment(loggedUser.getId(),
				appointment.getId());
		if (reservationCheck != null) {
			return null;
		}

		Reservation reservation = new Reservation();

		int totalPrice = 0;// promenjiva

		reservation.setUser(loggedUser);
		reservation.setAppointment(appointment);
		reservation.setStatus(ReservationStatus.SCHEDULED);

		reservationRepository.save(reservation);

		appointment.setStatus(AppointmentStatus.SCHEDULED);
		appointmentRepository.save(appointment);

		for (Long equipmentID : dto.getEquipmentIds()) {

			Equipment equipment = equipmentRepository.findById(equipmentID).orElse(null);

			totalPrice += equipment.getPrice();// ubacivanje

			ReservationEquipment reservationEquipment = new ReservationEquipment();
			reservationEquipment.setReservation(reservation);
			reservationEquipment.setEquipment(equipment);

			reservationEquipmentRepository.save(reservationEquipment);
		}
		reservation.setTotalPrice(reservation.getTotalPrice() + totalPrice);// setovanje

		reservationRepository.save(reservation);

		return reservation;
	}

	@Override
	public Reservation cancelReservation(long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
		if (reservation == null) {
			return null;
		}
		if (reservation.getAppointment().getDateAndTime().isBefore(LocalDateTime.now())) {
			return null;
		}

		// reservation.getAppointment().getDateAndTime(); vreme appointemnta
		reservation.setStatus(ReservationStatus.CANCELED);

		reservationRepository.save(reservation);

		User user = reservation.getUser();
		if (reservation.getAppointment().getDateAndTime().isBefore(LocalDateTime.now().plusHours(24))) {
			user.setPenals(user.getPenals() + 2);
		} else {
			user.setPenals(user.getPenals() + 1);
		}
		userRepository.save(user);

		Appointment appointment = reservation.getAppointment();
		appointment.setStatus(AppointmentStatus.FREE);
		appointmentRepository.save(appointment);

		return reservation;
	}

	@Override
	public List<Reservation> futureReservationsForUser(User loggedUser) {
		List<Reservation> futureReservations = reservationRepository.findFutureReservations(loggedUser.getId(),
				LocalDateTime.now());
		return futureReservations;
	}

	@Override
	public Reservation takeReservation(Long id) {
		Reservation reservation = reservationRepository.findById(id).orElse(null);

		if (reservation == null) {
			return null;
		}
		Appointment appointment = reservation.getAppointment();

		if (appointment.getStatus() != AppointmentStatus.SCHEDULED) {
			return null;
		}
		User user = reservation.getUser();

		if (appointment.getDateAndTime().isBefore(LocalDateTime.now())) {
			user.setPenals(2 + user.getPenals());
			userRepository.save(user);

			appointment.setStatus(AppointmentStatus.EXPIRED);
			appointmentRepository.save(appointment);
			return reservation;
		}
		appointment.setStatus(AppointmentStatus.TAKEN);
		appointmentRepository.save(appointment);

		reservation.setStatus(ReservationStatus.FINISHED);
		reservationRepository.save(reservation);

		emailService.sendEquipmentTakenConfirmation(reservation, findEquipmentByReservationId(reservation.getId()));
		return reservation;

	}

	@Override
	public List<Reservation> findReservationWithStatusScheduled() {

		return reservationRepository.findReservationWithStatusScheduled();

	}

	@Override
	public List<Reservation> findScehuledReservationsForAdmin(User loggedUser) {
		if (loggedUser.getCompany() == null) {
			return null;
		}
		long companyId = loggedUser.getCompany().getId();
		return reservationRepository.findScheduledReservationsForCompany(companyId);

	}

	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public List<Reservation> findReservationsByAppointmentCompanyId(Long companyId) {
		return reservationRepository.findReservationsByAppointmentCompanyId(companyId);

	}

	@Override
	public Reservation findReservationById(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
		if (reservation == null) {
			return null;
		}
		return reservation;
	}

	@Override
	public List<Equipment> findEquipmentByReservationId(Long id) {
		List<ReservationEquipment> reservationEquipments = reservationEquipmentRepository
				.findEquipmentByReservationId(id);

		List<Equipment> reservationEquipment = new ArrayList<Equipment>();
		for (ReservationEquipment reservationEquipmentIter : reservationEquipments) {
			reservationEquipment.add(reservationEquipmentIter.getEquipment());
		}

		return reservationEquipment;
	}

	@Override
	public Reservation findReservationScheduledCompanyIdUserId(Long userId, Long companyId) {
		Reservation reservation = reservationRepository.findReservationScheduledCompanyIdUserId(userId, companyId);
		if (reservation == null) {
			return null;
		}
		return reservation;
	}

	@Override
	public List<MonthIncomeDto> getMonthIncomes() {
		List<MonthIncomeDto> monthIncomeDto = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();

		for (int i = 1; i <= 12; i++) {
			LocalDate firstDayOfMonth = currentDate.minusMonths(i).withDayOfMonth(1);
			LocalDate lastDayOfMonth = currentDate.minusMonths(i).withDayOfMonth(firstDayOfMonth.lengthOfMonth());

			Integer monthIncome = reservationRepository.sumTotalPriceByDateRange(firstDayOfMonth.atStartOfDay(),
					lastDayOfMonth.atStartOfDay());
			if (monthIncome == null) {
				monthIncome = 0;
			}

			MonthIncomeDto dto = new MonthIncomeDto(firstDayOfMonth.getMonth().name(), monthIncome);
			monthIncomeDto.add(dto);
		}
		Collections.reverse(monthIncomeDto);

		return monthIncomeDto;
	}

	@Override
	public List<ReservationCountDto> getReservationCountForYear() {
		List<ReservationCountDto> reservationCountDtoList = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();

		for (int i = 1; i <= 12; i++) {
			LocalDate firstDayOfMonth = currentDate.minusMonths(i).withDayOfMonth(1);
			LocalDate lastDayOfMonth = currentDate.minusMonths(i).withDayOfMonth(firstDayOfMonth.lengthOfMonth());

			Integer count = reservationRepository.countReservationsByDateRange(firstDayOfMonth.atStartOfDay(),
					lastDayOfMonth.atStartOfDay());
			if (count == null) {
				count = 0;
			}

			ReservationCountDto dto = new ReservationCountDto(count, firstDayOfMonth);
			reservationCountDtoList.add(dto);
		}

		Collections.reverse(reservationCountDtoList);

		return reservationCountDtoList;
	}

	@Override
	public List<MonthIncomeForEachProduct> getMonthIncomeForEachProduct() {
		LocalDate currentDate = LocalDate.now();
		List<Equipment> equipments = equipmentRepository.findAll();
		
		List<MonthIncomeForEachProduct> monthIncomesForProducts = new ArrayList<MonthIncomeForEachProduct>();

		for (int i = 1; i <= 6; i++) {
			LocalDate firstDayOfMonth = currentDate.minusMonths(i).withDayOfMonth(1);
			LocalDate lastDayOfMonth = currentDate.minusMonths(i).withDayOfMonth(firstDayOfMonth.lengthOfMonth());
			
			MonthIncomeForEachProduct monthIncome = new MonthIncomeForEachProduct();
			monthIncome.setMonth(firstDayOfMonth.getMonth().name());
			
			for (Equipment equipment : equipments) {

				long reservationsMade = reservationEquipmentRepository.countReservationsByEquipmentIdAndDateRange(
						equipment.getId(), firstDayOfMonth.atStartOfDay(), lastDayOfMonth.atStartOfDay());
				
				ProductIncome productIncome = new ProductIncome(equipment.getName(), reservationsMade);
				monthIncome.getIncomes().add(productIncome);
				
			}
			

			monthIncomesForProducts.add(monthIncome);
		}
		Collections.reverse(monthIncomesForProducts);

		return monthIncomesForProducts;
	}

}
