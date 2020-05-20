package com.clinic.team16.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinic.team16.JWT.JwtToken;
import com.clinic.team16.beans.Role;
import com.clinic.team16.beans.User;
import com.clinic.team16.beans.DTO.UserAuthDTO;
import com.clinic.team16.beans.DTO.UserLoginDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicalCenterService;
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
		}catch(BadCredentialsException e) {
			return new ResponseEntity<UserLoginDTO>(new UserLoginDTO(null, null, null),HttpStatus.UNAUTHORIZED);
		}
		User user = (User)userService.loadUserByUsername(u.getEmail());
		
		final String jwtToken = jwtTokenUtility.generateToken(user);
		return new ResponseEntity<UserLoginDTO>(new UserLoginDTO(jwtToken, u.getEmail(),user.getRole().toString()),HttpStatus.OK);
/*		User k = userService.findOneByEmail(u.getEmail());
		System.out.println(k.getAddress());
		boolean ok = false;
		
		if(k != null) {
			if(u.getPassword().equalsIgnoreCase(k.getPassword())) ok = true;
		}
		
		if(ok) {
			return new ResponseEntity<User>(k, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(k, HttpStatus.BAD_REQUEST);
		}*/
	}
}
