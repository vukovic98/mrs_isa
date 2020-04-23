package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.repository.MedicationRepository;

@Service
public class MedicationService {
	@Autowired
	private MedicationRepository medicationRepository;
}
