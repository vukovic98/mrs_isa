/***********************************************************************
 * Module:  ClinicalCenterAdministrator.java
 * Author:  Vladimir
 * Purpose: Defines the Class ClinicalCenterAdministrator
 ***********************************************************************/
package com.clinic.team16.beans;
import java.util.*;

public class ClinicalCenterAdministrator extends User {
   public ClinicalCenter clinicalCenter;
   public ArrayList<RegistrationRequest> registrationRequest;
   public AppointmentRequest[] appointmentRequest;
   
   
   
   
   public ClinicalCenterAdministrator() {
	super();
}

public ClinicalCenterAdministrator(ClinicalCenter clinicalCenter, ArrayList<RegistrationRequest> registrationRequest,
		AppointmentRequest[] appointmentRequest) {
	super();
	this.clinicalCenter = clinicalCenter;
	this.registrationRequest = registrationRequest;
	this.appointmentRequest = appointmentRequest;
}



public ClinicalCenter getClinicalCenter() {
	return clinicalCenter;
}

public void setClinicalCenter(ClinicalCenter clinicalCenter) {
	this.clinicalCenter = clinicalCenter;
}

public AppointmentRequest[] getAppointmentRequest() {
	return appointmentRequest;
}

public void setAppointmentRequest(AppointmentRequest[] appointmentRequest) {
	this.appointmentRequest = appointmentRequest;
}

public ArrayList<RegistrationRequest> getRegistrationRequest() {
      if (registrationRequest == null)
         registrationRequest = new java.util.HashSet<RegistrationRequest>();
      return registrationRequest;
   }
   
   public java.util.Iterator getIteratorRegistrationRequest() {
      if (registrationRequest == null)
         registrationRequest = new ArrayList<RegistrationRequest>();
      return registrationRequest.iterator();
   }
   
   public void setRegistrationRequest(ArrayList<RegistrationRequest> newRegistrationRequest) {
      removeAllRegistrationRequest();
      for (java.util.Iterator iter = newRegistrationRequest.iterator(); iter.hasNext();)
         addRegistrationRequest((RegistrationRequest)iter.next());
   }
   
   public void addRegistrationRequest(RegistrationRequest newRegistrationRequest) {
      if (newRegistrationRequest == null)
         return;
      if (this.registrationRequest == null)
         this.registrationRequest = new ArrayList<RegistrationRequest>();
      if (!this.registrationRequest.contains(newRegistrationRequest))
      {
         this.registrationRequest.add(newRegistrationRequest);
         newRegistrationRequest.setClinicalCenterAdministrator(this);      
      }
   }
   
   public void removeRegistrationRequest(RegistrationRequest oldRegistrationRequest) {
      if (oldRegistrationRequest == null)
         return;
      if (this.registrationRequest != null)
         if (this.registrationRequest.contains(oldRegistrationRequest))
         {
            this.registrationRequest.remove(oldRegistrationRequest);
            oldRegistrationRequest.setClinicalCenterAdministrator((ClinicalCenterAdministrator)null);
         }
   }
   
   public void removeAllRegistrationRequest() {
      if (registrationRequest != null)
      {
         RegistrationRequest oldRegistrationRequest;
         for (java.util.Iterator iter = getIteratorRegistrationRequest(); iter.hasNext();)
         {
            oldRegistrationRequest = (RegistrationRequest)iter.next();
            iter.remove();
            oldRegistrationRequest.setClinicalCenterAdministrator((ClinicalCenterAdministrator)null);
         }
      }
   }

}