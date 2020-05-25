package com.clinic.team16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.repository.ClinicAdminRepository;

@Service
public class ClinicAdminService {

	
	@Autowired
	private ClinicAdminRepository adminRepository;
	
	public List<ClinicAdministrator> findAll() {
		return this.adminRepository.findAll();
	}

	public ClinicAdministrator findOneByEmail(String email) {
		return this.adminRepository.findOneByEmail(email);
	}

	public ClinicAdministrator save(ClinicAdministrator found) {
		// TODO Auto-generated method stub
		return this.adminRepository.save(found);
	}
}
