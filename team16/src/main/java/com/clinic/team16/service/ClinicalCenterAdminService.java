package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.repository.ClinicalCenterAdminRepository;

@Service
public class ClinicalCenterAdminService {

	@Autowired
	ClinicalCenterAdminRepository adminRepository;
}
