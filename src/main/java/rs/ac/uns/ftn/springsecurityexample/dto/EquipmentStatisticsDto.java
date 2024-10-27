package rs.ac.uns.ftn.springsecurityexample.dto;

public class EquipmentStatisticsDto {
	private String equipmentName;
	private long numberOfReservations;

	public EquipmentStatisticsDto() {
		super();
	}

	public EquipmentStatisticsDto(String equipmentName, int numberOfReservations) {
		super();
		this.equipmentName = equipmentName;
		this.numberOfReservations = numberOfReservations;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public long getNumberOfReservations() {
		return numberOfReservations;
	}

	public void setNumberOfReservations(long numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

	

}
