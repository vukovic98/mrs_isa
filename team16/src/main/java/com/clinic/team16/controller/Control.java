package com.clinic.team16.controller;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Control {

	   @RequestMapping(value = "/")
	   public String index() {
	      return "index";
	   }
	
	   @RequestMapping(value = "/doctor")
	   public String doctor() {
		   return "doctor";
	   }
}
