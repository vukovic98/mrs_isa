package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.repository.ClinicRepository;

@Service
public class ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;
	
	public List<Clinic> findAll() {
		return this.clinicRepository.findAll();
	}

	public Clinic findOneByClinicID(long i) {
		// TODO Auto-generated method stub
		return clinicRepository.findOneByClinicID(i);
	}
}
