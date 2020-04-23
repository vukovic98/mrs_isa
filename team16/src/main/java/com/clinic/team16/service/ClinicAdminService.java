package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.repository.ClinicAdminRepository;

@Service
public class ClinicAdminService {

	
	@Autowired
	private ClinicAdminRepository adminRepository;
}
