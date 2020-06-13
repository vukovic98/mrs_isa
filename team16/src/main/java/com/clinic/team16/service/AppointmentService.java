package com.clinic.team16.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	public ArrayList<Appointment> findByDoctorAndDate(String date, Long doctor_id) {
		return (ArrayList<Appointment>) this.appointmentRepository.findByDoctorAndDate(date, doctor_id);
	}
	
	public List<Appointment> findAll() {
		return this.appointmentRepository.findAll();
	}
	
	public Appointment findBuMedicalReport(long id) {
		return this.appointmentRepository.findByMedicalReport(id);
	} 
	
	public List<Appointment> findByDoctor(long id){
		return this.appointmentRepository.findByDoctor(id);
	}

	public Appointment findOneById(long id) {
		return this.appointmentRepository.findOneById(id);
	}
	
	public boolean checkUniqueConstraint(Date date, long id) {
		System.out.println("SIZE: " + this.appointmentRepository.checkUniqueConstraint(date, id).size());
		return this.appointmentRepository.checkUniqueConstraint(date, id).size() == 0 ? true : false;
	}
	
	public boolean save(Appointment a){
		Appointment ap = this.appointmentRepository.save(a); 
		
		if(ap != null)
			return true;
		else
			return false;
	}
	
	public boolean checkIfAppointmentExists(Doctor d, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ArrayList<Appointment> apps = (ArrayList<Appointment>) this.appointmentRepository.findByDoctorAndDate(sdf.format(date), d.getId());
		boolean exists = false;
		
		for(Appointment a : apps) {
			if(sdf.format(a.getDateTime()).substring(11, 16).equalsIgnoreCase(sdf.format(date).substring(11, 16))) {
				exists = true;
				break;
			}
		}
		
		return exists;
	}
	public boolean checkIfAppointmentIsScheduled(long appointmentId) {
		boolean isFree = false;
		Appointment a = this.appointmentRepository.findOneById(appointmentId);
		if (a.getPatient() == null) {
			isFree = true;
		}
		else {
			isFree = false;
		}
		return isFree;
	}
	
	public List<Appointment> findAllPredefined() {
		return this.appointmentRepository.findAllPredefined();
	}
	
	
	@Async
	public void sendMail(Doctor d, Patient p, String date) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo("mrs.isa2020@gmail.com");
			helper.setSubject("DIV Clinical Center - New Appointment");

			StringBuffer sb = new StringBuffer();

			sb.append("<h2>New appointment has been scheduled!</h2><br>");
			sb.append("<h3>Doctor: " + d.getFirstName() + " " + d.getLastName() + "</h3> <br><br>");
			sb.append("<h3>Patient: " + p.getFirstName() + " " + p.getLastName() + "</h3> <br><br>");
			sb.append("<h3>Date and time: " + date + "</h3> <br><br>");


			helper.setText(sb.toString(), true);
			this.javaMailSender.send(msg);

			System.out.println("SENT ACCEPTED MAIL!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Appointment> findAllForOrdination(long roomId) {
		return appointmentRepository.findAllForOrdination(roomId);
	}
}
