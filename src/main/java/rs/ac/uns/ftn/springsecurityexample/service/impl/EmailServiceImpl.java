package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Equipment;
import rs.ac.uns.ftn.springsecurityexample.model.Reservation;
import rs.ac.uns.ftn.springsecurityexample.model.User;

@Service
public class EmailServiceImpl {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

//	@Autowired
//	private QrCodeService qrCodeService;

	public void sendActivationCode(User user) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Account activation");
		
		//String activationURL = "http://localhost:8080/api/activateAccount/" + user.getActivationCode();

		mail.setText("Welcome to app");

		javaMailSender.send(mail);
	}

	
	public void sendEquipmentTakenConfirmation(Reservation reservation, List<Equipment> equipments) {
	    User user = reservation.getUser();
	    
	    // Build a string of equipment names
	    StringBuilder equipmentNames = new StringBuilder();
	    for (Equipment equipment : equipments) {
	        equipmentNames.append(equipment.getName()).append(", ");
	    }

	    // Remove the last comma and space, if present
	    if (equipmentNames.length() > 0) {
	        equipmentNames.setLength(equipmentNames.length() - 2); // Remove last comma and space
	    }

	    SimpleMailMessage mail = new SimpleMailMessage();
	    mail.setTo(user.getEmail());
	    mail.setFrom(env.getProperty("spring.mail.username"));
	    mail.setSubject("Equipment Taken Confirmation");
	    mail.setText("Dear " + user.getFirstName() + ",\n\n" +
	                 "You have successfully taken the following equipment: " + equipmentNames.toString() + ".\n" +
	                 "Reservation ID: " + reservation.getId() + "\n" +
	                 "Status: " + reservation.getStatus() + "\n\n" +
	                 "Thank you for using our service!\n" +
	                 "Best regards,\nYour Company Name");

	    javaMailSender.send(mail);
	}

}