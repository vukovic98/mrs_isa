package com.clinic.team16.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Ordination;
import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.OrdinationRepository;

@Service
public class OrdinationService {

	@Autowired
	private OrdinationRepository ordinationRepository;

	public List<Ordination> findAll() {
		return ordinationRepository.findAll();
	}


	
	public Ordination findOneByName(String name) {
		// TODO Auto-generated method stub
		return ordinationRepository.findOneByName(name);
	}
	
	public Ordination findOneByNameInClinic(String name, Long clinicId) {
		// TODO Auto-generated method stub
		return ordinationRepository.findOneByNameInClinic(name, clinicId);
	}

	public Ordination findOneByNumber(int number) {
		return ordinationRepository.findOneByNumber(number);
	}
	
	
	public Ordination save(Ordination ord) {
		// TODO Auto-generated method stub
		return ordinationRepository.save(ord);
	}

	public void delete(Ordination or) {
		ordinationRepository.delete(or);
		
	}
	
	
}
