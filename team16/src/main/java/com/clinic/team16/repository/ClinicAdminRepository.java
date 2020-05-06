package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Patient;

@Repository
public interface ClinicAdminRepository extends JpaRepository<ClinicAdministrator, Long>{

	public ClinicAdministrator findOneByEmail(String email);

}
