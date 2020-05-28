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
	
	public Clinic findOneByName(String name) {
		return this.clinicRepository.findOneByName(name);
	}
	
	public void save(Clinic c) {
		this.clinicRepository.save(c);
	}
}
