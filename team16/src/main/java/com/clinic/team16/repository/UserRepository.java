package com.clinic.team16.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.team16.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	public User findByEmail(String email);
	
	public User save(User u);
	
}
