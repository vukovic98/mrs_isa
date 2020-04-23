package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.repository.MedicalReportRepository;

@Service
public class MedicalReportService {
	@Autowired
	private MedicalReportRepository medicalReportRepository;
}
