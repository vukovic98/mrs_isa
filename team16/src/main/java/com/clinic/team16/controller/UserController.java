package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.UserService;

@RestController
@RequestMapping("/userApi")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/add")
	public String addUser(){
		User u = new User("s@s", "321", "Dusan", "Madzarevic", "Byulevar Despota Stefana 7a", "Novi Sad", "Srbija", "0669074444", "123456789", Role.CLINIC_ADMINISTRATOR);
		User u2 = new User("d@d", "5436", "Dusan", "Madzarevic", "Byulevar Despota Stefana 7a", "Novi Sad", "Srbija", "0669074444", "123456789", Role.DOCTOR);
		User u3 = new User("p@p", "123", "Ivana", "Vlaisavljevic", "Backa", "Backa", "Srbija", "0642351998", "1234567890", Role.PATIENT);
		User u4 = new User("a@a", "123", "Vladimir", "Vukovic", "Bulevar Despota Stefana 7a", "Novi Sad", "Srbija", "0669074444", "123456789", Role.CLINICAL_CENTER_ADMINISTRATOR);
		
		userService.save(u);
		userService.save(u2);
		userService.save(u3);
		userService.save(u4);
		
		return "SAVED";
	}
	
	@PostMapping(path = "/validateUser", consumes = "application/json")
	public ResponseEntity<User> validateUser(@RequestBody User u) {
		System.out.println(u.getEmail());
		User k = userService.findOneByEmail(u.getEmail());
		boolean ok = false;
		
		if(k != null) {
			if(u.getPassword().equalsIgnoreCase(k.getPassword())) ok = true;
		}
		
		if(ok) {
			return new ResponseEntity<User>(k, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(k, HttpStatus.BAD_REQUEST);
		}
	}
}
