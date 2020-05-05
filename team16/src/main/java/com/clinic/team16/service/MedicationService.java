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
}
