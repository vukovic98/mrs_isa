package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
	
	public Medication findOneByCode(String code);
	
}
