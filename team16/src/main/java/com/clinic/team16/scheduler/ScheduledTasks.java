package com.clinic.team16.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.clinic.team16.beans.Ordination;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clinic.team16.beans.Appointment;
import com.clinic.team16.beans.AppointmentRequest;
import com.clinic.team16.beans.Doctor;
import com.clinic.team16.service.AppointmentRequestService;
import com.clinic.team16.service.AppointmentService;
import com.clinic.team16.service.DoctorService;
import com.clinic.team16.service.OrdinationService;
@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);


	@Autowired
	private AppointmentRequestService appReqService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private OrdinationService ordinationService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	//@Scheduled(initialDelay = 10000000, fixedDelay = 100000)
	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void approveRequestsAutomatic() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<AppointmentRequest> list = appReqService.findAllUnapproved();
		Ordination chosenOrd = null;
		boolean available;
		for (AppointmentRequest appointmentRequest : list) {
			for (Ordination o : appointmentRequest.getAppointment().getDoctor().getClinic().getOrdinations()) {
				available = true;
				for(Appointment a : o.getAppointments()) {
					if(sdf.format(a.getDateTime()).equals(sdf.format(appointmentRequest.getAppointment().getDateTime()))) {
						available = false;
						break;
					}
				}
				if(available) {
					chosenOrd = o;
					break;
				}
									
				
			}
			if(chosenOrd != null) {
				appointmentRequest.setApproved(true);
				
				appointmentRequest.getAppointment().setOrdination(chosenOrd);
				chosenOrd.getAppointments().add(appointmentRequest.getAppointment());
				
				appointmentRequest.getAppointment().getDoctor().getAppointments().add(appointmentRequest.getAppointment());
				
				doctorService.save(appointmentRequest.getAppointment().getDoctor());
				
				
				appReqService.save(appointmentRequest);
				appointmentService.save(appointmentRequest.getAppointment());
				ordinationService.save(chosenOrd);
				appReqService.sendAcceptedMail(chosenOrd.getName(), appointmentRequest.getAppointment().getDoctor().getFirstName() + " "+ appointmentRequest.getAppointment().getDoctor().getLastName(), sdf.format(appointmentRequest.getAppointment().getDateTime()));

			}else {
				System.out.println(appointmentRequest.getAppointment().getDoctor().getClinic().getName());
				chosenOrd = appointmentRequest.getAppointment().getDoctor().getClinic().getOrdinations().get(0);
				appointmentRequest.setApproved(true);
				
				appointmentRequest.getAppointment().setOrdination(chosenOrd);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(chosenOrd.getAppointments().get(chosenOrd.getAppointments().size()-1).getDateTime());
				cal.add(Calendar.MINUTE, 30);
				
				chosenOrd.getAppointments().add(appointmentRequest.getAppointment());
				
				appointmentRequest.getAppointment().getDoctor().getAppointments().add(appointmentRequest.getAppointment());
				
				doctorService.save(appointmentRequest.getAppointment().getDoctor());
				
				
				appReqService.save(appointmentRequest);
				appointmentService.save(appointmentRequest.getAppointment());
				ordinationService.save(chosenOrd);
				appReqService.sendAcceptedMailChanged(chosenOrd.getName(), appointmentRequest.getAppointment().getDoctor().getFirstName() + " "+ appointmentRequest.getAppointment().getDoctor().getLastName(), sdf.format(appointmentRequest.getAppointment().getDateTime()));

			}
		}
		
	}
}
