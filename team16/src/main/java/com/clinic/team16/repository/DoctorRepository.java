package com.clinic.team16.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
 
	public Doctor findOneByEmail(String email);
	public Doctor findOneByid(long id);
	
	@Query(
			value = "SELECT * FROM doctor WHERE doctor.clinic_id = ?1",
			nativeQuery = true)
	public ArrayList<Doctor> findAllByClinic(long id);

}
