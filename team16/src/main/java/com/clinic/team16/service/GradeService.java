package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Grade;
import com.clinic.team16.repository.GradeRepository;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	public Grade save(Grade g) {
		return gradeRepository.save(g);
	}
}
