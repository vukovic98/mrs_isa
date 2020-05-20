package com.clinic.team16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
public class Team16Application {

	public static void main(String[] args) {
		
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","{}");
		
		SpringApplication.run(Team16Application.class, args);
	}

}
