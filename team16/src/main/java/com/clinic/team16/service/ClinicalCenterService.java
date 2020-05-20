package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.ClinicalCenter;
import com.clinic.team16.repository.ClinicalCenterRepository;

@Service
public class ClinicalCenterService {

	@Autowired
	private ClinicalCenterRepository clinicalCenterRepository;
	
	public ClinicalCenter findOneById(long id) {
		return this.clinicalCenterRepository.findByClinicId(id);
	}
}
