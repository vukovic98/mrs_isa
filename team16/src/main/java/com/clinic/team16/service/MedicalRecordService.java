package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.MedicalRecord;
import com.clinic.team16.beans.User;
import com.clinic.team16.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	public MedicalRecord save(MedicalRecord m) {
		return medicalRecordRepository.save(m);
	}
}
