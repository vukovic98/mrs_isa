package com.clinic.team16.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.AppointmentType;
import com.clinic.team16.beans.Clinic;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.beans.LeaveRequest;
import com.clinic.team16.beans.DTO.DoctorDTO;
import com.clinic.team16.repository.ClinicRepository;

@Service
public class ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

	@Autowired
	private AppointmentService appointmentService;

	public ArrayList<Clinic> findAll() {
		return (ArrayList<Clinic>) this.clinicRepository.findAll();
	}

	public Clinic findOneByClinicID(long i) {

		return clinicRepository.findOneByClinicID(i);
	}

	public List<Clinic> filterClinics(String appType) {
		return clinicRepository.filterClinics(appType);

	}

	public Clinic findOneByName(String name) {
		return this.clinicRepository.findOneByName(name);
	}

	public void save(Clinic c) {
		this.clinicRepository.save(c);
	}

	public ArrayList<DoctorDTO> filterDoctors(Clinic c, AppointmentType appType, String date) {
		ArrayList<DoctorDTO> doctors = new ArrayList<>();
		System.out.println("DATUM: "+date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateDate = null;
		
		
		try {
			dateDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(c.getDoctors().size());
		for (Doctor d : c.getDoctors()) {
			System.out.println(d.getFirstName() + "-------------------");
			if (d.getSpecialty() == appType) {
				List<LeaveRequest> leaves = d.getLeaveRequests();
				ArrayList<String> frontDates = new ArrayList<>();
				
				boolean leavesOk = true;
				boolean appointmentsOk = false;

				//leaves
				if (leaves.size() > 0 && leaves != null) {
					for (LeaveRequest l : leaves) {
						if (l.isApproved()) {
							boolean isBetween = l.getDateFrom().compareTo(dateDate)
									* dateDate.compareTo(l.getDateTo()) > 0;
							if (isBetween) {
								leavesOk = false;
								break;
							}
						}
					}
				}
				System.out.println(leavesOk);

				// appointments
				ArrayList<Appointment> apps = this.appointmentService.findByDoctorAndDate(sdf.format(dateDate), d.getId());
				System.out.println(apps.size());
				if (apps.size() < 15) {
					appointmentsOk = true;
					
					
					
					//slobdni termini
					ArrayList<String> possTerms = new ArrayList<>();
			            
					possTerms.add("08:00"); 
					possTerms.add("08:30"); 
					possTerms.add("09:00");
					possTerms.add("09:30");
					possTerms.add("10:00"); 
					possTerms.add("10:30"); 
					possTerms.add("11:00");
					possTerms.add("11:30");
					possTerms.add("12:00"); 
					possTerms.add("12:30"); 
					possTerms.add("13:00");
					possTerms.add("14:00"); 
					possTerms.add("14:30"); 
					possTerms.add("15:00");
					possTerms.add("15:30");
			            
			         
			        //2020-06-18 06:05:00.000000
			        for(Appointment a : apps) {
			        	String time = a.getDateTime().toString().substring(11, 16);
			        	System.out.println(time + "****************************");
			        	if(possTerms.contains(time)) {
			        		System.out.println("UDJE" + time);
			        		possTerms.remove(time);
			        	}
			        }
			        
			        for(String p : possTerms) {
			        	String finalDate = date + " " + p;
		        		frontDates.add(finalDate);
			        }
					
				} else {
					appointmentsOk = false;
				}
				System.out.println(appointmentsOk);
				
				

				if (leavesOk && appointmentsOk) {
					doctors.add(new DoctorDTO(d.getId(), d.getFirstName(), d.getLastName(), d.getAverageGrade(), d.getEmail(), frontDates));
				}

			}
			System.out.println("--------------------------");
		}

		return doctors;
	}
}
