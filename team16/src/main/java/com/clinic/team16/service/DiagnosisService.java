package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Diagnosis;
import com.clinic.team16.repository.DiagnosisRepository;

@Service
public class DiagnosisService {
	
	@Autowired
	private DiagnosisRepository diagnosisRepository;
	
	public List<Diagnosis> findAll() {
		return this.diagnosisRepository.findAll();
	}

	public Diagnosis findOneByCode(String code) {
		return this.diagnosisRepository.findOneByCode(code);
	}
	
	public Diagnosis save(Diagnosis s) {
		return this.diagnosisRepository.save(s);
	}
	
	public void delete(Diagnosis s) {
		this.diagnosisRepository.delete(s);
	}
	
	public Diagnosis findOneByName(String name) {
		return this.diagnosisRepository.findOneByName(name);
	}
}
