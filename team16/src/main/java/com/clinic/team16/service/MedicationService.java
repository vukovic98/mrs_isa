package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Medication;
import com.clinic.team16.repository.MedicationRepository;

@Service
public class MedicationService {
	
	@Autowired
	private MedicationRepository medicationRepository;
	
	public List<Medication> findAll() {
		return this.medicationRepository.findAll();
	}
	
	public Medication findOneByCode(String code) {
		return this.medicationRepository.findOneByCode(code);
	}
	
	public Medication save(Medication m) {
		return this.medicationRepository.save(m);
	}
	
	public void delete(Medication m) {
		this.medicationRepository.delete(m);
	}
}
