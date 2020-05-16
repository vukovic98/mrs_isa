package com.clinic.team16.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.MedicalReport;
import com.clinic.team16.repository.MedicalReportRepository;

@Service
public class MedicalReportService {
	@Autowired
	private MedicalReportRepository medicalReportRepository;

	public boolean save(MedicalReport m) {
		try {
			this.medicalReportRepository.save(m);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public MedicalReport findOneById(long id) {
		return this.medicalReportRepository.findOneByMedicalReportId(id);
	}
}
