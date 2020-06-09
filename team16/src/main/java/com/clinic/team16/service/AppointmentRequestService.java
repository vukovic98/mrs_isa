package com.clinic.team16.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.AppointmentRequest;
import com.clinic.team16.repository.AppointmentRequestRepository;

@Service
public class AppointmentRequestService {

	@Autowired
	private AppointmentRequestRepository requestRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void save(AppointmentRequest req) {
		this.requestRepository.save(req);
	}
	
	
	public List<AppointmentRequest> findAll(){
		return this.requestRepository.findAll();
	}
	
	public AppointmentRequest findOneByAppointmentRequestId(long id) {
		return requestRepository.findOneByAppointmentRequestId(id);
	}


	public List<AppointmentRequest> findAllUnapproved() {
		
		return requestRepository.findAllUnapproved();
	}
	
	@Async
	public void sendAcceptedMail(String room, String doctor, String date) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo("mrs.isa2020@gmail.com");
			helper.setSubject("DIV Clinical Center - Appointment approved");

			StringBuffer sb = new StringBuffer();

			sb.append("<h2>Your appointment has been approved!</h2><br>");
			sb.append(String.format("You have been assigned the room %s, at %s, and the assigned doctor is %s .",room,date,doctor));

			helper.setText(sb.toString(), true);
			this.javaMailSender.send(msg);

			System.out.println("SENT ACCEPTED MAIL!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
