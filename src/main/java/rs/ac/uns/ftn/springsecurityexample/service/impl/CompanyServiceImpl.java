package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDto;
import rs.ac.uns.ftn.springsecurityexample.dto.CompanySearchDto;
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentStatisticsDto;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;
import rs.ac.uns.ftn.springsecurityexample.repository.CompanyRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.EquipmentRepository;
import rs.ac.uns.ftn.springsecurityexample.repository.ReservationEquipmentRepository;
import rs.ac.uns.ftn.springsecurityexample.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	@Autowired
	private ReservationEquipmentRepository reservationEquipmentRepository;
	
	

	public Company create(CompanyDto dto) { 
		Company company = dto.mapToCompany();
		company.setAverageGrade(0);
		company.setWorksFrom(LocalTime.now());		//kasnije kroz dto
		company.setWorksTo(LocalTime.now());
		company = companyRepository.save(company);
		return company;
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public List<Company> filterBy(CompanySearchDto searchDto) {

		List<Company> companies = companyRepository.searchCompanies(searchDto.getName(), searchDto.getAdress(),
				searchDto.getGradeFrom(), searchDto.getGradeTo());
		return companies;

	}

	@Override
	public Company getById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<EquipmentStatisticsDto> getEquipmentStatistics(User loggedUser){
		if(loggedUser.getCompany() == null) {
			return null;
		}
		long companyId = loggedUser.getCompany().getId();
		List<Equipment> equipments = equipmentRepository.findByCompanyId(companyId);
		
		List<EquipmentStatisticsDto> equipmentStatisticsDtos = new ArrayList<EquipmentStatisticsDto>();
		
		for(Equipment equipment : equipments) {
			Long numberOfReservations = reservationEquipmentRepository.countReservationsByEquipmentId(equipment.getId());
			EquipmentStatisticsDto equipmentStatisticsDto = new EquipmentStatisticsDto();
			equipmentStatisticsDto.setEquipmentName(equipment.getName());
			equipmentStatisticsDto.setNumberOfReservations(numberOfReservations);
			equipmentStatisticsDtos.add(equipmentStatisticsDto);
		}
		
		return equipmentStatisticsDtos;
	}
	
	

	

}