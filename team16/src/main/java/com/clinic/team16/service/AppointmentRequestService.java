package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.AppointmentRequest;
import com.clinic.team16.repository.AppointmentRequestRepository;

@Service
public class AppointmentRequestService {

	@Autowired
	private AppointmentRequestRepository requestRepository;
	
	public void save(AppointmentRequest req) {
		this.requestRepository.save(req);
	}
}
