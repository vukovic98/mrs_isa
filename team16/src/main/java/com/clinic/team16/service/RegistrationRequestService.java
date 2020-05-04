package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.RegistrationRequest;
import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.RegistrationRequestRepository;

@Service
public class RegistrationRequestService {

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;
	
	public List<RegistrationRequest> findAll() {
		return this.registrationRequestRepository.findAll();
	}
	
	public RegistrationRequest save(RegistrationRequest p) {
		return this.registrationRequestRepository.save(p);
	}
	
	
}
