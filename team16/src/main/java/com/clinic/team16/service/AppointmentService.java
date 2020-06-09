package com.clinic.team16.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

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
}
