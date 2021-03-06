package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.MedicalReport;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport,Long> {

	public MedicalReport findOneByMedicalReportId(long id);
}
