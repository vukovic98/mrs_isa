package com.clinic.team16.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.repository.DoctorRepository;

@Service
public class DoctorService {
    @Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor create(Doctor doc) throws Exception{
		System.out.println("Pozvana metoda servisa za dodavanje doktora.");
		Doctor savedDoc = doctorRepository.save(doc);
		return savedDoc;
	}

	public Doctor findOneByEmail(String email) {
		return doctorRepository.findOneByEmail(email);
	}
	
	public ArrayList<Doctor> findAllByClinic(long id) {
		return this.doctorRepository.findAllByClinic(id);
	}
	
	public Doctor findOneByDoctorID(long id) {
		return doctorRepository.findOneByid(id);
	}
	public List<Doctor> findAll() {
		return this.doctorRepository.findAll();
	}
	public Doctor save(Doctor d) {
		return this.doctorRepository.save(d);
	}
	
	
	
}
