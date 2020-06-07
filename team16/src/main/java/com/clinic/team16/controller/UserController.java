package com.clinic.team16.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.JWT.JwtToken;
import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.UserAuthDTO;
import com.clinic.team16.beans.DTO.UserLoginDTO;
import com.clinic.team16.service.UserService;

@RestController
@RequestMapping("/userApi")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtToken jwtTokenUtility;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping(path = "/validateUser", consumes = "application/json")
	public ResponseEntity<UserLoginDTO> validateUser(@RequestBody UserAuthDTO u) {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<UserLoginDTO>(new UserLoginDTO(null, null, null), HttpStatus.FORBIDDEN);
		}

		User ua = userService.findOneByEmail(u.getEmail());

		if (ua != null) {
			User user = new User(u.getEmail(), ua.getPassword(), ua.getFirstName(), ua.getLastName(), ua.getAddress(),
					ua.getCity(), ua.getCountry(), ua.getPhoneNumber(), ua.getInsuranceNumber(), ua.getRole());

			final String jwtToken = jwtTokenUtility.generateToken(user);
			return new ResponseEntity<UserLoginDTO>(new UserLoginDTO(jwtToken, u.getEmail(), user.getRole().toString()),
					HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/sendMail")
	public String sendMail() throws MessagingException {

		this.userService.sendMail();

		return ("SENT!");
	}
	
	@GetMapping(path = "/getRole")
	public ResponseEntity<Role> getRole() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User u = this.userService.findOneByEmail(currentUser);
		
		if(u != null) {
			return new ResponseEntity<Role>(u.getRole(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
