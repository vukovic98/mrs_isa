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
	   
	   @RequestMapping(value = "/patientApi/index")
	   public String patientApiIndex() {
		   return "redirect:index";
	   }
	
	   @RequestMapping(value = "/doctor")
	   public String doctor() {
		   return "doctor";
	   }
	   
	   @RequestMapping(value = "/doctorAppointments")
	   public String doctorAppointments() {
		   return "doctorAppointments";
	   }
	   
	   @RequestMapping(value = "/workSchedule")
	   public String workSchedule() {
		   return "workSchedule";
	   }
	   
	   @RequestMapping(value = "/doctorProfile")
	   public String doctorProfile() {
		   return "doctor";
	   }
	   
	   @RequestMapping(value = "/nurse")
	   public String nurse() {
		   return "nurse";
	   }
	   
	   
	   @RequestMapping(value = "/patient")
	   public String patient() {
		   return "patient";
	   }
	   
	   @RequestMapping(value = "/clinicalCenterAdmin")
	   public String clinicalCenterAdmin() {
		   return "clinicalCenterAdmin";
	   }
	   
	   @RequestMapping(value = "/clinicAdmin")
	   public String clinicAdmin() {
		   return "clinicAdmin";
	   }
	   
	   @RequestMapping(value = "/businessReports")
	   public String businessReports() {
		   return "businessReports";
	   }
	   
	   @RequestMapping(value = "/doctors")
	   public String doctors() {
		   return "doctors";
	   }
	   
	   @RequestMapping(value = "/rooms")
	   public String rooms() {
		   return "rooms";
	   }
	   
	   @RequestMapping(value = "/pricelist")
	   public String pricelist() {
		   return "pricelist";
	   }
	   
	   @RequestMapping(value = "/predefinedAppointments")
	   public String predefinedAppointments() {
		   return "predefinedAppointments";
	   }
	   
	   @RequestMapping(value = "/clinics")
	   public String clinics() {
		   return "clinics";
	   }
	   
	   @RequestMapping(value = "/clinicalAdmins")
	   public String clinicalAdmins() {
		   return "clinicalAdmins";
	   }
	   
	   @RequestMapping(value = "/medications")
	   public String medications() {
		   return "medications";
	   }
	   
	   @RequestMapping(value = "/medicalRecord")
	   public String medicalRecord() {
		   return "medicalRecord";
	   }
	   
	   @RequestMapping(value = "/patientsClinics")
	   public String patientsClinics() {
		   return "patientsClinics";
	   }
	   
	   @RequestMapping(value = "/appointmentHistory")
	   public String appointmentHistory() {
		   return "appointmentHistory";
	   }
	   
	   @RequestMapping(value = "/diagnosis")
	   public String diagnosis() {
		   return "diagnosis";
	   }
	   
	   @RequestMapping(value = "/medicalReports")
	   public String medicalReports() {
		   return "medicalReports";
	   }
	   
	   @RequestMapping(value = "/workCalendar")
	   public String workCalendar() {
		   return "workCalendar";
	   }
	   
	   @RequestMapping(value = "/leaveRequest")
	   public String leaveRequest() {
		   return "leaveRequest";
	   }
	   
	   @RequestMapping(value = "/nurseProfile")
	   public String nurseProfile() {
		   return "nurseProfile";
	   }
	   @RequestMapping(value = "/makeAppointment")
	   public String makeAppointment() {
		   return "makeAppointment";
	   }
}
