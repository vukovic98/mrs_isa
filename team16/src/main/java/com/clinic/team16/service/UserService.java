package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.User;
import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.UserRepository;

import java.util.*;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findOneByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User save(User u) {
		return userRepository.save(u);
	}
}
