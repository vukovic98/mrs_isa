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
		User u = new User("s@s", "321", "Dusan", "Madzarevic", "Byulevar Despota Stefana 7a", "Novi Sad", "Srbija", "0669074444", "123456789");
		User u2 = new User("d@d", "5436", "Dusan", "Madzarevic", "Byulevar Despota Stefana 7a", "Novi Sad", "Srbija", "0669074444", "123456789");

		userService.save(u);
		userService.save(u2);
		return "SAVED";
	}
	
	@PostMapping(path = "/validateUser", consumes = "application/json")
	public ResponseEntity<User> validateUser(@RequestBody User u) {
		System.out.println(u.getEmail());
		List<User> k = userService.findOneByEmail(u.getEmail());
		boolean found = false;
		User f = new User();

		if (k != null) {
			for (User us : k) {
				System.out.println(us.getEmail());
				if (us.getEmail().equalsIgnoreCase(u.getEmail())) {
					found = true;
					f = us;
					break;
				}
			}
		}
		System.out.println(found);
		
		if(found) {
			return new ResponseEntity<User>(f, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(f, HttpStatus.BAD_REQUEST);
		}
	}
}
