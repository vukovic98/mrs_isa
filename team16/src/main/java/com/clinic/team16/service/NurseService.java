package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Nurse;
import com.clinic.team16.repository.NurseRepository;

@Service
public class NurseService{	@Autowired
	private NurseRepository nurseRepository;

	public List<Nurse> findAll() {
		return this.nurseRepository.findAll();
	}
	
	public Nurse findOneById(long id) {
		return this.nurseRepository.findOneById(id);
	}
	
	public Nurse findOneByEmail(String email) {
		return this.nurseRepository.findOneByEmail(email);
	}
	
	public Nurse save(Nurse n) {
		return this.nurseRepository.save(n);
	}
}
