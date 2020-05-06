package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Doctor> findAll() {
		return this.doctorRepository.findAll();
	}
	
	
	
}
