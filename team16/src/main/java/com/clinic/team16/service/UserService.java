package com.clinic.team16.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

import com.clinic.team16.beans.User;
import com.clinic.team16.repository.AppointmentRepository;
import com.clinic.team16.repository.UserRepository;

import java.util.*;

import javax.mail.internet.MimeMessage;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	public User findOneByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User save(User u) {
		return userRepository.save(u);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}
		
	@Async
	public void sendMail() {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();

			// true = multipart message
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo("mrs.isa2020@gmail.com");

			helper.setSubject("Testing from Spring Boot");

			// default = text/plain
			// helper.setText("Check attachment for image!");

			// true = text/html
			helper.setText("<h1><i>Check attachment for image!</i></h1>", true);

			// hard coded a file path
			// FileSystemResource file = new FileSystemResource(new
			// File("path/android.png"));

			this.javaMailSender.send(msg);
			System.out.println("SENT!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
