package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.OrdinationRepository;

@Service
public class OrdinationService {

	@Autowired
	private OrdinationRepository ordinationRepository;
}
