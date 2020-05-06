package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.MedicalRecord;
import com.clinic.team16.beans.User;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
	public MedicalRecord save(MedicalRecord m);
}
