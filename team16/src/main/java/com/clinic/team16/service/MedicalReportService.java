package com.clinic.team16.service;


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
			MedicalReport mO = this.medicalReportRepository.save(m);
			if(mO != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public MedicalReport findOneById(long id) {
		return this.medicalReportRepository.findOneByMedicalReportId(id);
	}
}
