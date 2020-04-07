package com.clinic.team16.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.Nurse;

@Repository
public class NurseRepository {
	
	private ArrayList<Nurse> nurses = new ArrayList<>();
	
	public Nurse create(Nurse nurse) {
		this.nurses.add(nurse);
		
		return nurse;
	}
}
