package com.clinic.team16.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.TransientObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.AppointmentRequest;
import com.clinic.team16.beans.ClinicalCenterAdministrator;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.MedicalReport;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.AppointmentDTO;
import com.clinic.team16.beans.DTO.AppointmentRequestDTO;
import com.clinic.team16.beans.DTO.CalendarDataDTO;
import com.clinic.team16.beans.DTO.MedicalReportDTO;
import com.clinic.team16.service.AppointmentRequestService;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.ClinicalCenterAdminService;
import com.clinic.team16.service.DoctorService;
import com.clinic.team16.service.PatientService;

@RestController
@RequestMapping("/appointmentApi")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ClinicalCenterAdminService clinicalCenterAdminService;

	@Autowired
	private AppointmentRequestService appointmentRequestService;

	@Autowired
	private PatientService patientService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<AppointmentDTO>> findAll() {
		List<Appointment> list = this.appointmentService.findAll();
		List<AppointmentDTO> dtoList = new ArrayList<AppointmentDTO>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (list != null) {
			for (Appointment a : list) {
				String doctor = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
				String patient = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
				dtoList.add(new AppointmentDTO(a.getAppointmentId(), formatter.format(a.getDateTime()), a.getDuration(),
						doctor, patient, a.getPricelistItems().getName(),
						String.valueOf(a.getPricelistItems().getPrice()),
						String.valueOf(a.getOrdination().getNumber())));
			}
			return new ResponseEntity<List<AppointmentDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/findAllDoctorAppointments")
	public ResponseEntity<ArrayList<CalendarDataDTO>> getCalendarDates() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Doctor d = this.doctorService.findOneByEmail(currentUser);

		if (d != null) {
			ArrayList<Appointment> list = (ArrayList<Appointment>) this.appointmentService.findByDoctor(d.getId());
			ArrayList<CalendarDataDTO> dtoList = new ArrayList<>();

			if (list != null) {
				for (Appointment a : list) {
					String name = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
					String docName = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
					dtoList.add(new CalendarDataDTO(formatter.format(a.getDateTime()), name, docName,
							a.getOrdination().getType().toString(), a.getDuration()));
				}

				return new ResponseEntity<ArrayList<CalendarDataDTO>>(dtoList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/findAllDoc/{doctorId}")
	public ResponseEntity<List<AppointmentDTO>> findAllDoctor(@PathVariable("doctorId") long doctorId) {
		List<Appointment> list = this.appointmentService.findByDoctor(doctorId);
		List<AppointmentDTO> dtoList = new ArrayList<AppointmentDTO>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (list != null) {
			for (Appointment a : list) {
				String doctor = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
				String patient = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
				dtoList.add(new AppointmentDTO(a.getAppointmentId(), formatter.format(a.getDateTime()), a.getDuration(),
						doctor, patient, a.getPricelistItems().getName(),
						String.valueOf(a.getPricelistItems().getPrice()),
						String.valueOf(a.getOrdination().getNumber())));
			}
			return new ResponseEntity<List<AppointmentDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping(path = "/findAllMedicalReports")
	public ResponseEntity<List<MedicalReportDTO>> findMedicalReports() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Appointment> list = this.appointmentService.findAll();
		List<MedicalReportDTO> dtoList = new ArrayList<MedicalReportDTO>();

		if (list != null) {
			for (Appointment a : list) {
				if (a.getMedicalReport() != null) {
				if (a.getMedicalReport().getApproved() == false
						&& a.getMedicalReport().getNurse().getEmail().equalsIgnoreCase(currentUser)) {
					String doctor = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
					String patient = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
					dtoList.add(new MedicalReportDTO(a.getMedicalReport().getMedicalReportId(),
							a.getMedicalReport().getDetails(), doctor, patient, a.getDoctor().getClinic().getName(),
							a.getMedicalReport().getMedication(), a.getMedicalReport().getDiagnosis(),
							a.getMedicalReport().getNurse().getId()));
				}}
			}
			return new ResponseEntity<List<MedicalReportDTO>>(dtoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/findReportById", consumes = "application/json")
	public ResponseEntity<MedicalReportDTO> findReportById(@RequestBody MedicalReport m) {
		long id = m.getMedicalReportId();

		Appointment a = this.appointmentService.findBuMedicalReport(id);

		if (a != null) {
			String doctor = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
			String patient = a.getPatient().getFirstName() + " " + a.getPatient().getLastName();
			MedicalReportDTO mDTO = new MedicalReportDTO(a.getMedicalReport().getMedicalReportId(),
					a.getMedicalReport().getDetails(), doctor, patient, a.getDoctor().getClinic().getName(),
					a.getMedicalReport().getMedication(), a.getMedicalReport().getDiagnosis(),
					a.getMedicalReport().getNurse().getId());
			System.out.println(a.getMedicalReport().getMedication());
			System.out.println(a.getDoctor().getClinic().getName());
			return new ResponseEntity<MedicalReportDTO>(mDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(path = "/addAppointment", consumes = "application/json")
	public ResponseEntity<HttpStatus> addAppointment(@RequestBody AppointmentRequestDTO request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Doctor d = this.doctorService.findOneByEmail(request.getDoctor());
		Patient p = this.patientService.findOneByEmail(request.getEmail());
		ClinicalCenterAdministrator admin = this.clinicalCenterAdminService.findMainClinicalCenterAdmin();
		PricelistItem found = null;

		boolean exists = true;

		if (d != null) {
			for (PricelistItem pI : d.getClinic().getPricelist().getPricelistItems()) {
				if (pI.getName() == request.getExamType()) {
					found = pI;
				}
			}

			try {
				exists = this.appointmentService.checkIfAppointmentExists(d, sdf.parse(request.getDateTime()));
			} catch (ParseException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			if (!exists) {
				// dodavanje appointmenta
				try {
					if (this.appointmentService.checkUniqueConstraint(sdf.parse(request.getDateTime()), d.getId())) {
						AppointmentRequest appReq = new AppointmentRequest(false, sdf.parse(request.getDateTime()),
								admin, null);
						this.appointmentRequestService.save(appReq);

						admin.addAppointmentRequest(appReq);
						this.clinicalCenterAdminService.save(admin);

						Appointment app = new Appointment(0, sdf.parse(request.getDateTime()), 0, null, null, appReq, d,
								p, found);

						p.addAppointment(app);
						this.appointmentService.save(app);

						System.out.println("NASTAVI");
						this.patientService.save(p);

						appReq.setAppointment(app);
						this.appointmentRequestService.save(appReq);
						
						return new ResponseEntity<>(HttpStatus.OK);
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return null;
	}

}
