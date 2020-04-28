package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
	
}
