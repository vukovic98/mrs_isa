package com.clinic.team16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Team16Application {

	public static void main(String[] args) {
		
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","{}");
		
		SpringApplication.run(Team16Application.class, args);
	}

}
