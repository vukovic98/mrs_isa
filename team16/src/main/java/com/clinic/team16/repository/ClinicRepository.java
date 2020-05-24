package com.clinic.team16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.ClinicalCenter;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	public Clinic findOneByClinicID(long i);

}
