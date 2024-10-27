package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.Equipment;

public class EquipmentDto {

	private Long id;
	private String name;
	private String description;
	private int price;
	private long companyId;
	private String companyName;

	public EquipmentDto() {
		super();
	}

	public EquipmentDto(Equipment equipment) {
		id = equipment.getId();
		name = equipment.getName();
		description = equipment.getDescription();
		price = equipment.getPrice();
		companyId = equipment.getCompany().getId();
		companyName = equipment.getCompany().getName();
	}

	public Equipment mapToEquipment() {
		Equipment equipment = new Equipment();
		equipment.setName(name);
		equipment.setDescription(description);
		equipment.setPrice(price);

		return equipment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
