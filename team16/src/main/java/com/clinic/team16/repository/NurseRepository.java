package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
	
	public Nurse findOneById(long id);
	
	public Nurse findOneByEmail(String email);
	
	@Query(value = "SELECT * FROM nurse WHERE nurse.nurse_clinic_id = ?1",
			nativeQuery = true)
	public ArrayList<Nurse> findAllByClinic(long clinic_id);
	
}
