package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Nurse;
import com.clinic.team16.repository.NurseRepository;

@Service
public interface NurseService extends JpaRepository<Nurse, Long>{
	
	@Autowired
	private NurseRepository nurseRepository;
	}
