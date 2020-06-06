package com.clinic.team16.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Clinic;
import com.clinic.team16.repository.ClinicRepository;

@Service
public class ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

	public ArrayList<Clinic> findAll() {
		return (ArrayList<Clinic>) this.clinicRepository.findAll();
	}

	public Clinic findOneByClinicID(long i) {

		return clinicRepository.findOneByClinicID(i);
	}
 
	public List<Clinic> filterClinics(AppointmentType appType){
		return clinicRepository.filterClinics(appType);
		
	}
	public Clinic findOneByName(String name) {
		return this.clinicRepository.findOneByName(name);
	}

	public void save(Clinic c) {
		this.clinicRepository.save(c);
	}
}
