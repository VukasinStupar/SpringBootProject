package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentDto;
import rs.ac.uns.ftn.springsecurityexample.dto.EquipmentSearchDto;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface EquipmentService {

	Equipment create(EquipmentDto dto, User loggedUser);

	ArrayList<Equipment> getAllByCompanyId(long id);

	public List<Equipment> searchEquipment(EquipmentSearchDto equipmentSearchDto);

	Equipment deleteEquipment(long id);

	List<Equipment> getAll();

}
