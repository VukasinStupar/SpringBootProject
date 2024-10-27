package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentDto;
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentSearchDto;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.ReservationEquipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.CompanyRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.EquipmentRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ReservationEquipmentRepository;
import rs.ac.uns.ftn.springsecurityexample.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Autowired
	private ReservationEquipmentRepository reservationEquipmentRepository;

	

	@Override
	public Equipment create(EquipmentDto dto, User loggedUser) {
		
		Company company = loggedUser.getCompany();
		if (company == null) {
			return null;
		}
		Equipment equipment = dto.mapToEquipment();
		equipment.setCompany(company);

		equipment = equipmentRepository.save(equipment);
		return equipment;
	}

	@Override
	public Equipment deleteEquipment(long id) {
		Equipment equipment = equipmentRepository.findById(id).orElse(null);
		if (equipment == null) {
			return null;
		}

		List<ReservationEquipment> reservationEquipment = reservationEquipmentRepository
				.findReservationByEquipmentId(id);

		if (reservationEquipment.size() > 0) {
			return null;
		}
		equipmentRepository.delete(equipment);
		return equipment;
	}

	@Override
	public ArrayList<Equipment> getAllByCompanyId(long id) {
		return equipmentRepository.findAllByCompanyId(id);
	}

	@Override
	public List<Equipment> searchEquipment(EquipmentSearchDto equipmentSearchDto) {
		List<Equipment> equipment = equipmentRepository.searchEquipment(equipmentSearchDto.getName(),
				equipmentSearchDto.getDescription());
		return equipment;
	}

	@Override
	public List<Equipment> getAll() {
		return equipmentRepository.findAll();
	}

}
