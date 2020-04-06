package com.clinic.team16.control;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Control {

	@GetMapping("/greeting")
	public String helloWorld() {
		System.out.println("evo ovde");
		return "Hello World!";
	}
	
}
