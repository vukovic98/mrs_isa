package com.clinic.team16.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinic.team16.beans.Patient;
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
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@GetMapping(path = "/sendMail")
	public void sendMail() throws MessagingException {
		MimeMessage msg = this.javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("mrs.isa2020@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1><i>Check attachment for image!</i></h1>", true);

		// hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
        
        this.javaMailSender.send(msg);
        
        System.out.println("SENT!");
	}
	
	@PostMapping(path = "/validateUser", consumes = "application/json")
	public ResponseEntity<User> validateUser(@RequestBody User u) {
		System.out.println(u.getEmail());
		User k = userService.findOneByEmail(u.getEmail());
		boolean ok = false;
		
		if(k != null) {
			if(u.getPassword().equals(k.getPassword())) {
				if(u instanceof Patient) {
					if(((Patient) u).getMedicalRecord() != null) ok = true;
					else ok = false;
				} else {
					ok = true;
				}
			} else {
				ok = false;
			}
		}
		
		if(ok) {
			return new ResponseEntity<User>(k, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(k, HttpStatus.BAD_REQUEST);
		}
	}
}
