package com.clinic.team16.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.MedicalReport;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.DTO.AppointmentDTO;
import com.clinic.team16.beans.DTO.MedicalReportDTO;
import com.clinic.team16.service.AppointmentService;

@RestController
@RequestMapping("/appointmentApi")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping(path = "/findAll") 
	public ResponseEntity<List<Appointment>> findAll() {
		List<Appointment> list = this.appointmentService.findAll();
		
		if(list != null)
			return new ResponseEntity<List<Appointment>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/findAllDoc/{doctorId}")
	public ResponseEntity<List<AppointmentDTO>> findAllDoctor(@PathVariable("doctorId") long doctorId){
		List<Appointment> list = this.appointmentService.findByDoctor(doctorId);
		List<AppointmentDTO> dtoList = new ArrayList<AppointmentDTO>();
		
		if(list != null) {
			for(Appointment a : list) {
				String doctor = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
				String patient = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
				dtoList.add(new AppointmentDTO(a.getAppointmentId(), a.getDateTime(), a.getDuration(), doctor, patient));
			}
			return new ResponseEntity<List<AppointmentDTO>>(dtoList, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping(path = "/findAllMedicalReports")
	public ResponseEntity<List<MedicalReportDTO>> findMedicalReports() {
		List<Appointment> list = this.appointmentService.findAll();
		List<MedicalReportDTO> dtoList = new ArrayList<MedicalReportDTO>();
		
		if(list != null) {
			for(Appointment a : list) {
				if(a.getMedicalReport().getApproved() == false && a.getMedicalReport().getNurse().getId() == 67) {
					String doctor = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
					String patient = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
					dtoList.add(new MedicalReportDTO(a.getMedicalReport().getMedicalReportId(),
							a.getMedicalReport().getDetails(), doctor, patient, a.getDoctor().getClinic().getName(), 
							a.getMedicalReport().getMedication(), a.getMedicalReport().getDiagnosis()));
				}
			}
			return new ResponseEntity<List<MedicalReportDTO>>(dtoList, HttpStatus.OK);
		} else 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(path = "/findReportById", consumes = "application/json")
	public ResponseEntity<MedicalReportDTO> findReportById(@RequestBody MedicalReport m) {
		long id = m.getMedicalReportId();
		
		Appointment a = this.appointmentService.findBuMedicalReport(id);
		
		if(a != null) {
			String doctor = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
			String patient = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
			MedicalReportDTO mDTO = new MedicalReportDTO(a.getMedicalReport().getMedicalReportId(),
					a.getMedicalReport().getDetails(), doctor, patient, a.getDoctor().getClinic().getName(),
					a.getMedicalReport().getMedication(), a.getMedicalReport().getDiagnosis());
			System.out.println(a.getMedicalReport().getMedication());
			System.out.println(a.getDoctor().getClinic().getName());
			return new ResponseEntity<MedicalReportDTO>(mDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
