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
				ArrayList<Appointment> apps = this.appointmentService.findByDoctorAndDate(sdf.format(dateDate),
						d.getId());
				System.out.println(apps.size());
				if (apps.size() < 15) {
					appointmentsOk = true;
				} else {
					appointmentsOk = false;
				}
				System.out.println(appointmentsOk);

				if (leavesOk && appointmentsOk) {
					doctors.add(new DoctorDTO(d.getId(), d.getFirstName(), d.getLastName(), d.getAverageGrade()));
				}

			}
			System.out.println("--------------------------");
		}

		return doctors;
	}
}
