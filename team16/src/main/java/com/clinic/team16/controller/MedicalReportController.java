package com.clinic.team16.controller;

import java.util.ArrayList;

import javax.transaction.Transactional;

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
import com.clinic.team16.beans.Diagnosis;
import com.clinic.team16.beans.MedicalReport;
import com.clinic.team16.beans.Medication;
import com.clinic.team16.beans.Nurse;
import com.clinic.team16.beans.DTO.MedicalReportDTO;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.DiagnosisService;
import com.clinic.team16.service.MedicalReportService;
import com.clinic.team16.service.MedicationService;
import com.clinic.team16.service.NurseService;

@RestController
@RequestMapping("/medicalReportApi")
public class MedicalReportController {

	@Autowired
	private MedicalReportService medicalReportService;

	@Autowired
	private NurseService nurseService;

	@Autowired
	private DiagnosisService diagnosisService;

	@Autowired
	private MedicationService medicationService;

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping(path = "/addReport", consumes = "application/json")
	public ResponseEntity<HttpStatus> addReport(@RequestBody MedicalReportDTO report) {
		System.out.println(report.getId());
		MedicalReport m = this.medicalReportService.findOneById(report.getId());

		if (m != null) {
			m.setApproved(true);
			m.setDetails(report.getDetails());

			boolean ok = this.medicalReportService.save(m);

			if (ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Transactional
	@PostMapping(path = "/createReport", consumes = "application/json")
	public ResponseEntity<HttpStatus> createReport(@RequestBody MedicalReportDTO report) {
		String[] params = report.getClinic().split(",");
		Nurse n = this.nurseService.findOneByEmail(params[0]); // nurse Email -> clinic
		Appointment a = this.appointmentService.findOneById(report.getId());

		ArrayList<Diagnosis> diag = new ArrayList<>();
		ArrayList<Medication> med = new ArrayList<>();

		for (String s : report.getDiagnosis()) {
			diag.add(this.diagnosisService.findOneByName(s));
		}

		for (String s : report.getMedications()) {
			med.add(this.medicationService.findOneByName(s));
		}

		MedicalReport m = new MedicalReport(report.getDetails(), false, diag, med, n);
		boolean okM = this.medicalReportService.save(m);

		a.setMedicalReport(m);
		a.setDuration(Double.parseDouble(params[1]));
		boolean okA = this.appointmentService.save(a);

		n.addMedicalReport(m);
		Nurse ok = this.nurseService.save(n);

		if (okM && okA && ok != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/getReportForAppointment/{appId}")
	public ResponseEntity<MedicalReportDTO> getReportForAppointment(@PathVariable long appId) {
		Appointment a = this.appointmentService.findOneById(appId);

		if (a != null) {
			MedicalReport m = a.getMedicalReport();
			String doc = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
			String pat = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();

			MedicalReportDTO mDTO = new MedicalReportDTO(m.getMedicalReportId(), m.getDetails(), doc, pat,
					a.getDoctor().getClinic().getName(), m.getMedication(), m.getDiagnosis(), m.getNurse().getId());

			return new ResponseEntity<>(mDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
