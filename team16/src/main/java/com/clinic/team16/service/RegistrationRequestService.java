package com.clinic.team16.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.RegistrationRequestRepository;

@Service
public class RegistrationRequestService {

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	public List<RegistrationRequest> findAll() {
		return this.registrationRequestRepository.findAllUnapproved();
	}

	public RegistrationRequest save(RegistrationRequest p) {
		return this.registrationRequestRepository.save(p);
	}

	public RegistrationRequest findOneByUserId(long id) {
		return this.registrationRequestRepository.findOneByUserId(id);
	}

	public boolean delete(RegistrationRequest r) {
		try {
			this.registrationRequestRepository.delete(r);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteBonds(long id) {
		try {
			this.registrationRequestRepository.deleteBonds(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Async
	public void sendAcceptedMail(String email) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo("mrs.isa2020@gmail.com");
			helper.setSubject("DIV Clinical Center");

			StringBuffer sb = new StringBuffer();

			sb.append("<h2>Your registration request for our DIV clinical center was accepted!</h2><br>");
			sb.append("<h3>Please follow the link bellow to activate your accont:</h3> <br><br>");
			sb.append("http://localhost:8080/patientApi/activateAccount?email=" + email);

			helper.setText(sb.toString(), true);
			this.javaMailSender.send(msg);

			System.out.println("SENT ACCEPTED MAIL!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Async
	public void sendDeclinedMail(String reason) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();

	        // true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	        helper.setTo("mrs.isa2020@gmail.com");

	        helper.setSubject("DIV Clinical Center");
	        
	        StringBuffer sb = new StringBuffer();

	        sb.append("<h2>We are sorry, your registration request was declined.</h2><br>");
	        sb.append("<h3>Management cites this as a reason:</h3> <br><br>");
	        sb.append("\"<i>" + reason + "</i>\"");
	        

	        helper.setText(sb.toString(), true);

	        this.javaMailSender.send(msg);
	        
	        System.out.println("SENT DECLINED MAIL!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
