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
		return this.registrationRequestRepository.findAllUnapproved();
	}
	
	public RegistrationRequest save(RegistrationRequest p) {
		return this.registrationRequestRepository.save(p);
	}
	
	public RegistrationRequest findOneByUserId(long id) {
		return this.registrationRequestRepository.findOneByUserId(id);
	}
	
	public boolean delete(RegistrationRequest r) {
		try {
			this.registrationRequestRepository.delete(r);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteBonds(long id) {
		try {
			this.registrationRequestRepository.deleteBonds(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
