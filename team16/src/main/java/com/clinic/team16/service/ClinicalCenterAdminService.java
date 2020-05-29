package com.clinic.team16.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.ClinicalCenterAdministrator;
import com.clinic.team16.repository.ClinicalCenterAdminRepository;

@Service
public class ClinicalCenterAdminService {

	@Autowired
	private ClinicalCenterAdminRepository adminRepository;
	
	public ClinicalCenterAdministrator finOneById(long id) {
		return this.adminRepository.findOneById(id);
	}
	
	public ClinicalCenterAdministrator save(ClinicalCenterAdministrator a) {
		return this.adminRepository.save(a);
	}
	
	public ArrayList<ClinicalCenterAdministrator> findAll() {
		return (ArrayList<ClinicalCenterAdministrator>) this.adminRepository.findAllAdmins();
	}
	
	public ClinicalCenterAdministrator findOneByEmail(String email) {
		return this.adminRepository.findOneByEmail(email);
	}
}
