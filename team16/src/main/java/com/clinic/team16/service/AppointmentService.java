package com.clinic.team16.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	
	
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
}
