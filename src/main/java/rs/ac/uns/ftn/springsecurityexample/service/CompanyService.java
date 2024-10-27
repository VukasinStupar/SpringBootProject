package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;
import java.util.Optional;

import rs.ac.uns.ftn.springsecurityexample.dto.CompanyDto;
import rs.ac.uns.ftn.springsecurityexample.dto.CompanySearchDto;
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentStatisticsDto;
import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface CompanyService {
	Company create(CompanyDto dto);

	List<Company> filterBy(CompanySearchDto searchDto);

	List<Company> findAll();
	
	Company getById(Long id);
	
	List<EquipmentStatisticsDto> getEquipmentStatistics(User loggedUser);

}
