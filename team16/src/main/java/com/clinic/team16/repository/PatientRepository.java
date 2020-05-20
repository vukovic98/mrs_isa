package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinic.team16.beans.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	public Patient findOneByEmail(String email);
	
	

}
