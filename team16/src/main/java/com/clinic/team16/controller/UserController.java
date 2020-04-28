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

import com.clinic.team16.beans.User;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicalCenterService;
import com.clinic.team16.service.UserService;

@RestController
@RequestMapping("/userApi")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/validateUser", consumes = "application/json")
	public ResponseEntity<User> validateUser(@RequestBody User u) {

		List<User> k = userService.findOneByEmail(u.getEmail());
		boolean found = false;
		User f = new User();

		if (k != null) {
			for (User us : k) {
				if (us.getEmail().equalsIgnoreCase(u.getEmail())) {
					found = true;
					f = us;
					break;
				}
			}
		}
		
		return new ResponseEntity<>(new User(f), HttpStatus.OK);
	}
}
