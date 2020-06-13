package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Diagnosis;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

	public Diagnosis findOneByCode(String code);
	
	public Diagnosis findOneByName(String name);
	
	@Query(
			value = "SELECT * FROM diagnosis",
			nativeQuery = true)
	public ArrayList<Diagnosis> findAllDiagnosis();
	
}
