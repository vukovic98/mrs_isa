package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Nurse;
import com.clinic.team16.repository.NurseRepository;

@Service
public class NurseService {
	
	@Autowired
	private NurseRepository nurseRepository;
	
	public Nurse create(Nurse nurse) {
		Nurse savedNurse = nurseRepository.create(nurse);
		
		return savedNurse;
	}
}
