package com.clinic.team16.service;

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
		Doctor savedDoc = doctorRepository.create(doc);
		return savedDoc;
	}
}
