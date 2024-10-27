package rs.ac.uns.ftn.springsecurityexample.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReservationEquipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "equipment_id", nullable = false)
	private Equipment equipment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;
	
	
	public ReservationEquipment() {
		super();
	}

	
	public ReservationEquipment(Long id, Equipment equipment, Reservation reservation) {
		super();
		this.id = id;
		this.equipment = equipment;
		this.reservation = reservation;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Equipment getEquipment() {
		return equipment;
	}


	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


	public Reservation getReservation() {
		return reservation;
	}


	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	
	
}