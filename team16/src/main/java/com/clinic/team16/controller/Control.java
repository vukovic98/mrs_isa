package com.clinic.team16.controller;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Control {

	  @RequestMapping(value = "/", method = RequestMethod.GET)
	   public String index() {
	      return "index";
	   }
	   
	  @RequestMapping(value = "/patientApi/index", method = RequestMethod.GET)
	   public String patientApiIndex() {
		   return "redirect:index";
	   }
	

	   @RequestMapping(value = "/leaveRequestAdmin", method = RequestMethod.GET)
	   public String leaveRequestAdmin() {
		   return "leaveRequestAdmin";
	   }
	   
		

	   @RequestMapping(value = "/clinicAppointmentRequests", method = RequestMethod.GET)
	   public String clinicAppointmentRequests() {
		   return "clinicAppointmentRequests";
	   }
	   

	   @RequestMapping(value = "/doctor", method = RequestMethod.GET)
	   public String doctor() {
		   return "doctor";
	   }
	   

	   @RequestMapping(value = "/doctorLeave", method = RequestMethod.GET)
	   public String doctorLeave() {
		   return "doctorLeave";
	   }
	   
	   @RequestMapping(value="/searchDoctors", method = RequestMethod.GET)
	   public String searchDoctors() {
		   return "searchDoctors";
	   }
	   

	   @RequestMapping(value = "/clinicalCenterAdminInitial", method = RequestMethod.GET)
	   public String clinicalCenterAdminInitial() {
		   return "clinicalCenterAdminInitial";
	   }
	   

	   @RequestMapping(value = "/centerAdmins", method = RequestMethod.GET)
	   public String centerAdmins() {
		   return "centerAdmins";
	   }
	   

	   @RequestMapping(value = "/doctorAppointments", method = RequestMethod.GET)
	   public String doctorAppointments() {
		   return "doctorAppointments";
	   }
	   

	   @RequestMapping(value = "/workSchedule", method = RequestMethod.GET)
	   public String workSchedule() {
		   return "workSchedule";
	   }
	   

	   @RequestMapping(value = "/doctorProfile", method = RequestMethod.GET)
	   public String doctorProfile() {
		   return "doctorProfile";
	   }
	   

	   @RequestMapping(value = "/nurse", method = RequestMethod.GET)
	   public String nurse() {
		   return "nurse";
	   }
	   
	   

	   @RequestMapping(value = "/patient", method = RequestMethod.GET)
	   public String patient() {
		   return "patient";
	   }
	   

	   @RequestMapping(value = "/clinicalCenterAdmin", method = RequestMethod.GET)
	   public String clinicalCenterAdmin() {
		   return "clinicalCenterAdmin";
	   }
	   

	   @RequestMapping(value = "/clinicAdmin", method = RequestMethod.GET)
	   public String clinicAdmin() {
		   return "clinicAdmin";
	   }
	   

	   @RequestMapping(value = "/businessReports", method = RequestMethod.GET)
	   public String businessReports() {
		   return "businessReports";
	   }
	   

	   @RequestMapping(value = "/doctors", method = RequestMethod.GET)
	   public String doctors() {
		   return "doctors";
	   }
	   

	   @RequestMapping(value = "/rooms", method = RequestMethod.GET)
	   public String rooms() {
		   return "rooms";
	   }
	   

	   @RequestMapping(value = "/pricelist", method = RequestMethod.GET)
	   public String pricelist() {
		   return "pricelist";
	   }
	   

	   @RequestMapping(value = "/predefinedAppointments", method = RequestMethod.GET)
	   public String predefinedAppointments() {
		   return "predefinedAppointments";
	   }
	   

	   @RequestMapping(value = "/clinics", method = RequestMethod.GET)
	   public String clinics() {
		   return "clinics";
	   }
	   

	   @RequestMapping(value = "/clinicalAdmins", method = RequestMethod.GET)
	   public String clinicalAdmins() {
		   return "clinicalAdmins";
	   }
	   

	   @RequestMapping(value = "/medications", method = RequestMethod.GET)
	   public String medications() {
		   return "medications";
	   }
	   

	   @RequestMapping(value = "/medicalRecord", method = RequestMethod.GET)
	   public String medicalRecord() {
		   return "medicalRecord";
	   }
	   

	   @RequestMapping(value = "/patientsClinics", method = RequestMethod.GET)
	   public String patientsClinics() {
		   return "patientsClinics";
	   }
	   

	   @RequestMapping(value = "/appointmentHistory", method = RequestMethod.GET)
	   public String appointmentHistory() {
		   return "appointmentHistory";
	   }
	   

	   @RequestMapping(value = "/diagnosis", method = RequestMethod.GET)
	   public String diagnosis() {
		   return "diagnosis";
	   }
	   

	   @RequestMapping(value = "/medicalReports", method = RequestMethod.GET)
	   public String medicalReports() {
		   return "medicalReports";
	   }
	   

	   @RequestMapping(value = "/workCalendar", method = RequestMethod.GET)
	   public String workCalendar() {
		   return "workCalendar";
	   }
	   

	   @RequestMapping(value = "/leaveRequest", method = RequestMethod.GET)
	   public String leaveRequest() {
		   return "leaveRequest";
	   }
	   

	   @RequestMapping(value = "/nurseProfile", method = RequestMethod.GET)
	   public String nurseProfile() {
		   return "nurseProfile";
	   }

	   @RequestMapping(value = "/makeAppointment", method = RequestMethod.GET)
	   public String makeAppointment() {
		   return "makeAppointment";
	   }
	   

	   @RequestMapping(value = "/clinicPage", method = RequestMethod.GET)
	   public String clinicPage() {
		   return "clinicPage";
	   }
}