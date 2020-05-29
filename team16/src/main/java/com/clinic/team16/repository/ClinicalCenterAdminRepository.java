package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.ClinicalCenterAdministrator;

@Repository
public interface ClinicalCenterAdminRepository extends JpaRepository<ClinicalCenterAdministrator, Long>{
	
	public ClinicalCenterAdministrator findOneById(long id);
	
	@Query(
			value = "SELECT * FROM clinical_center_administrator WHERE email = ?1",
			nativeQuery = true)
	public ClinicalCenterAdministrator findOneByEmail(String email);
	
	
	@Query(
			value = "SELECT * FROM clinical_center_administrator WHERE role = 'CLINICAL_CENTER_ADMINISTRATOR'",
			nativeQuery = true)
	public List<ClinicalCenterAdministrator> findAllAdmins();
}
