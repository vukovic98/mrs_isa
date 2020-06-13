package com.clinic.team16.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Clinic;

import com.clinic.team16.beans.ClinicAdministrator;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.Grade;
import com.clinic.team16.beans.Ordination;
import com.clinic.team16.beans.Patient;
import com.clinic.team16.beans.Pricelist;
import com.clinic.team16.beans.PricelistItem;
import com.clinic.team16.beans.DTO.ClinicAddDTO;
import com.clinic.team16.beans.DTO.ClinicFilterDTO;
import com.clinic.team16.beans.DTO.ClinicInfoDTO;
import com.clinic.team16.beans.DTO.DoctorAvgDTO;
import com.clinic.team16.beans.DTO.DoctorDTO;
import com.clinic.team16.beans.DTO.GraphDTO;
import com.clinic.team16.service.ClinicAdminService;

import com.clinic.team16.service.ClinicService;
import com.clinic.team16.service.GradeService;
import com.clinic.team16.service.PatientService;
import com.clinic.team16.service.PricelistService;

@RestController
@RequestMapping("/clinicApi")
public class ClinicController {

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private PricelistService pricelistService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private ClinicAdminService adminService;

	@GetMapping(path = "/findAll")
	public ResponseEntity<ArrayList<ClinicInfoDTO>> findAll() {
		ArrayList<Clinic> list = this.clinicService.findAll();
		if (list != null) {
			ArrayList<ClinicInfoDTO> daoList = new ArrayList<ClinicInfoDTO>();
			for (Clinic c : list) {
				daoList.add(new ClinicInfoDTO(c.getClinicID(), c.getName(), c.getAddress(), c.getDescription(),
						c.getAverageGrade(), c.getCity()));
			}
			return new ResponseEntity<ArrayList<ClinicInfoDTO>>(daoList, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping(path = "/addClinic", consumes = "application/json")
	public ResponseEntity<HttpStatus> addClinic(@RequestBody ClinicAddDTO clinic) {
		Clinic found = this.clinicService.findOneByName(clinic.getName());

		if (found == null) {
			Pricelist p = this.pricelistService.findById(clinic.getPricelist());

			if (p != null) {
				Clinic c = new Clinic(clinic.getName(), clinic.getAddress(), clinic.getDescription(), null, p, null,
						null, null, null, clinic.getCity());

				p.addClinic(c);
				this.clinicService.save(c);

				this.pricelistService.save(p);

				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAppointments/{appType}&{date}&{avgGrade}&{location}")
	public ResponseEntity<List<ClinicFilterDTO>> findAppointments(@PathVariable AppointmentType appType,
			@PathVariable String date, @PathVariable String avgGrade, @PathVariable String location)
			throws ParseException {
		List<Clinic> list = this.clinicService.filterClinics(appType.toString());
		List<ClinicFilterDTO> dtoList = new ArrayList<ClinicFilterDTO>();
		double price = 0.0;
		if (list != null) {
			for (Clinic c : list) {
				System.out.println("ima");
				for (PricelistItem pli : c.getPricelist().getPricelistItems()) {
					if (pli.getName() == appType) {
						price = pli.getPrice();
						break;
					}
				}

				boolean rightGrade = false;

				if (!avgGrade.equalsIgnoreCase("null")) {
					if (Math.ceil(c.getAverageGrade()) >= Double.parseDouble(avgGrade)) {
						rightGrade = true;
					}
				} else {
					rightGrade = true;
				}

				boolean rightLocation = false;

				if (!location.equalsIgnoreCase("null")) {
					if (c.getCity().equalsIgnoreCase(location)) {
						rightLocation = true;
					}
				} else {
					rightLocation = true;
				}

				if (rightGrade && rightLocation) {
					System.out.println("DATUM U /findapp :" + date);
					ArrayList<DoctorDTO> doctors = this.clinicService.filterDoctors(c, appType, date);
					if (doctors.size() != 0) {
						dtoList.add(new ClinicFilterDTO(c.getClinicID(), c.getName(), c.getAddress(),
								c.getAverageGrade(), appType, price, c.getCity()));
					}
				}
			}
			return new ResponseEntity<List<ClinicFilterDTO>>(dtoList, HttpStatus.OK);

		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PutMapping(path = "/rateClinic/{clinicID}&{grade}", consumes = "application/json")
	public ResponseEntity<HttpStatus> rateClinic(@PathVariable("clinicID") long clinicID,
			@PathVariable("grade") String grade) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		long id = clinicID;
		Clinic c = this.clinicService.findOneByClinicID(id);
		Patient p = this.patientService.findOneByEmail(currentUser);
		if (c != null && p != null) {
			Grade g = c.addGrade(p, Integer.parseInt(grade));
			gradeService.save(g);
			clinicService.save(c);
			patientService.save(p);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/findAllAppointmentDoctors/{clinicID}&{appType}&{date}")
	public ResponseEntity<ArrayList<DoctorDTO>> findAllAppointmentDoctors(@PathVariable long clinicID,
			@PathVariable AppointmentType appType, @PathVariable String date) {
		Clinic c = this.clinicService.findOneByClinicID(clinicID);

		ArrayList<DoctorDTO> doctors = this.clinicService.filterDoctors(c, appType, date);

		if (doctors != null)
			return new ResponseEntity<ArrayList<DoctorDTO>>(doctors, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/getClinicGrade/{clinicID}")
	public ResponseEntity<ClinicInfoDTO> clinicGrade(@PathVariable("clinicID") long clinicID) {
		Clinic c = this.clinicService.findOneByClinicID(clinicID);
		if (c != null) {
			ClinicInfoDTO cdt = new ClinicInfoDTO(clinicID, c.getName(), c.getAddress(), c.getDescription(),
					c.getAverageGrade(), c.getCity());

			return new ResponseEntity<ClinicInfoDTO>(cdt, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/getDoctorsGrade/{clinicID}")
	public ResponseEntity<List<DoctorAvgDTO>> doctorsGrade(@PathVariable("clinicID") long clinicID) {
		Clinic c = this.clinicService.findOneByClinicID(clinicID);
		
		List<DoctorAvgDTO> dtoList = new ArrayList<DoctorAvgDTO>();
		if (c != null) {
			for (Doctor d : c.getDoctors()) {
				dtoList.add(new DoctorAvgDTO(d.getFirstName() + " " + d.getLastName(), d.getAverageGrade()));
			}

			return new ResponseEntity<List<DoctorAvgDTO>>(dtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping(path = "/getCurrentClinic")
	public ResponseEntity<Long> currentClinic() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);

		if (ca != null) {
			return new ResponseEntity<Long>(ca.getClinic().getClinicID(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@GetMapping(path = "/getCurrentClinicInfo")
	public ResponseEntity<ClinicInfoDTO> currentClinicInfo() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		ClinicAdministrator ca = adminService.findOneByEmail(currentUser);
		ClinicInfoDTO cl;
		Clinic currCl = clinicService.findOneByClinicID(ca.getClinic().getClinicID());

		if (currCl != null) {
			cl = new ClinicInfoDTO(currCl.getClinicID(), currCl.getName(), currCl.getAddress(), currCl.getDescription(),
					currCl.getAverageGrade(), currCl.getCity());
			return new ResponseEntity<ClinicInfoDTO>(cl, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/getClinicInfo/{clinicID}")
	public ResponseEntity<ClinicInfoDTO> getClinicInfoById(@PathVariable long clinicID) {
		Clinic currCl = clinicService.findOneByClinicID(clinicID);

		if (currCl != null) {
			ClinicInfoDTO cl = new ClinicInfoDTO(currCl.getClinicID(), currCl.getName(), currCl.getAddress(),
					currCl.getDescription(), currCl.getAverageGrade(), currCl.getCity());
			return new ResponseEntity<ClinicInfoDTO>(cl, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "/createMap/{address}")
	public ResponseEntity<String> createMap(@PathVariable String address) {
		address = address.replace(" ", "+");
		System.out.println("UDJE");
		
		String ONE_CALL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		HttpURLConnection con = null;
		InputStream is = null;

		try {
			String url_str = ONE_CALL + address + "&key=AIzaSyAXySxAYZiompb27Oq1f3XD6vrL4jpp3AU";
			con = (HttpURLConnection) (new URL(url_str)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();

			// Let's read the response
			StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null)
				buffer.append(line + "\r\n");

			is.close();
			con.disconnect();
			System.out.println(buffer.toString());
			return new ResponseEntity<String>(buffer.toString(), HttpStatus.OK);
			
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Throwable t) {
			}
			try {
				con.disconnect();
			} catch (Throwable t) {
			}
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@GetMapping(path = "/getDailyAppointments/{clinicId}/{money}")
	public ResponseEntity<List<GraphDTO>> getDailyAppointments(@PathVariable("clinicId") long clinicID, @PathVariable("money") long money){
		Clinic currCl = clinicService.findOneByClinicID(clinicID);
		
		List<GraphDTO> dtoList = new ArrayList<GraphDTO>();
		dtoList.add(new GraphDTO("Sunday", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Monday", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Tuesday", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Wednesday", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Thursday", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Friday", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Saturday", Double.valueOf(0)));
		
		
		for (Ordination ord : currCl.getOrdinations()) {
			for (Appointment apt : ord.getAppointments()) {
				  Calendar currentCalendar = Calendar.getInstance();
				  int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
				  int year = currentCalendar.get(Calendar.YEAR);
				  Calendar targetCalendar = Calendar.getInstance();
				  targetCalendar.setTime(apt.getDateTime());
				  int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
				  int targetYear = targetCalendar.get(Calendar.YEAR);
				  if(week == targetWeek && year == targetYear) {
					  if(money == 0)
						  dtoList.get(targetCalendar.get(Calendar.DAY_OF_WEEK) - 1).setY(dtoList.get(targetCalendar.get(Calendar.DAY_OF_WEEK) - 1).getY() + 1);
					  else
						  dtoList.get(targetCalendar.get(Calendar.DAY_OF_WEEK) - 1).setY(dtoList.get(targetCalendar.get(Calendar.DAY_OF_WEEK) - 1).getY() + apt.getPrice());

				  }
			}
		}
		
		return new ResponseEntity<List<GraphDTO>>(dtoList, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/getMonthlyAppointments/{clinicId}/{money}")
	public ResponseEntity<List<GraphDTO>> getMonthlyAppointments(@PathVariable("clinicId") long clinicID, @PathVariable("money") long money){
		Clinic currCl = clinicService.findOneByClinicID(clinicID);
		
		List<GraphDTO> dtoList = new ArrayList<GraphDTO>();
		dtoList.add(new GraphDTO("First week", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Second week", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Third week", Double.valueOf(0)));
		dtoList.add(new GraphDTO("Fourth week", Double.valueOf(0)));
		
		
		for (Ordination ord : currCl.getOrdinations()) {
			for (Appointment apt : ord.getAppointments()) {
				  Calendar currentCalendar = Calendar.getInstance();
				  int year = currentCalendar.get(Calendar.YEAR);
				  int month = currentCalendar.get(Calendar.MONTH);
				  Calendar targetCalendar = Calendar.getInstance();
				  targetCalendar.setTime(apt.getDateTime());
				  int targetYear = targetCalendar.get(Calendar.YEAR);
				  int targetMonth = currentCalendar.get(Calendar.MONTH);
				  if(year == targetYear && month == targetMonth) {
					  if(money == 0)
						  dtoList.get(targetCalendar.get(Calendar.WEEK_OF_MONTH) - 1).setY(dtoList.get(targetCalendar.get(Calendar.WEEK_OF_MONTH) - 1).getY() + 1);
					  else
						  dtoList.get(targetCalendar.get(Calendar.WEEK_OF_MONTH) - 1).setY(dtoList.get(targetCalendar.get(Calendar.WEEK_OF_MONTH) - 1).getY() + apt.getPrice());

				  }
			}
		}
		
		return new ResponseEntity<List<GraphDTO>>(dtoList, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/getYearlyAppointments/{clinicId}/{money}")
	public ResponseEntity<List<GraphDTO>> getYearlyAppointments(@PathVariable("clinicId") long clinicID, @PathVariable("money") long money){
		Clinic currCl = clinicService.findOneByClinicID(clinicID);
		
		List<GraphDTO> dtoList = new ArrayList<GraphDTO>();
		dtoList.add(new GraphDTO("January", Double.valueOf(0)));
		dtoList.add(new GraphDTO("February", Double.valueOf(0)));
		dtoList.add(new GraphDTO("March", Double.valueOf(0)));
		dtoList.add(new GraphDTO("April", Double.valueOf(0)));
		dtoList.add(new GraphDTO("May", Double.valueOf(0)));
		dtoList.add(new GraphDTO("June", Double.valueOf(0)));
		dtoList.add(new GraphDTO("July", Double.valueOf(0)));
		dtoList.add(new GraphDTO("August", Double.valueOf(0)));
		dtoList.add(new GraphDTO("September", Double.valueOf(0)));
		dtoList.add(new GraphDTO("October", Double.valueOf(0)));
		dtoList.add(new GraphDTO("November", Double.valueOf(0)));
		dtoList.add(new GraphDTO("December", Double.valueOf(0)));
		
		for (Ordination ord : currCl.getOrdinations()) {
			for (Appointment apt : ord.getAppointments()) {
				  Calendar currentCalendar = Calendar.getInstance();
				  int year = currentCalendar.get(Calendar.YEAR);
				  Calendar targetCalendar = Calendar.getInstance();
				  targetCalendar.setTime(apt.getDateTime());
				  int targetYear = targetCalendar.get(Calendar.YEAR);
				  if(year == targetYear) {
					  if(money == 0)
						  dtoList.get(targetCalendar.get(Calendar.MONTH)).setY(dtoList.get(targetCalendar.get(Calendar.MONTH)).getY() + 1);
					  else
						  dtoList.get(targetCalendar.get(Calendar.MONTH)).setY(dtoList.get(targetCalendar.get(Calendar.MONTH)).getY() + apt.getPrice());

				  }
			}
		}
		
		return new ResponseEntity<List<GraphDTO>>(dtoList, HttpStatus.OK);
		
	}
	

}
