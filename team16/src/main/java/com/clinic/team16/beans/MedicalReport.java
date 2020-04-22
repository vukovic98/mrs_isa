/***********************************************************************
 * Module:  MedicalReport.java
 * Author:  Vladimir
 * Purpose: Defines the Class MedicalReport
 ***********************************************************************/
package com.clinic.team16.beans;
import java.util.*;

public class MedicalReport {
   private String details;
   private Boolean approved;
   
   public ArrayList<Diagnosis> diagnosis;
   public ArrayList<Medication> medication;
   public Nurse nurse;
   
   
   
   
   public String getDetails() {
	return details;
}

public void setDetails(String details) {
	this.details = details;
}

public Boolean getApproved() {
	return approved;
}

public void setApproved(Boolean approved) {
	this.approved = approved;
}

public ArrayList<Diagnosis> getDiagnosis() {
	return diagnosis;
}

public void setDiagnosis(ArrayList<Diagnosis> diagnosis) {
	this.diagnosis = diagnosis;
}

public ArrayList<Medication> getMedication() {
	return medication;
}

public void setMedication(ArrayList<Medication> medication) {
	this.medication = medication;
}

public MedicalReport(String details, Boolean approved, ArrayList<Diagnosis> diagnosis, ArrayList<Medication> medication,
		Nurse nurse) {
	super();
	this.details = details;
	this.approved = approved;
	this.diagnosis = diagnosis;
	this.medication = medication;
	this.nurse = nurse;
}

public MedicalReport() {
	super();
}

public Nurse getNurse() {
      return nurse;
   }
   
   public void setNurse(Nurse newNurse) {
      if (this.nurse == null || !this.nurse.equals(newNurse))
      {
         if (this.nurse != null)
         {
            Nurse oldNurse = this.nurse;
            this.nurse = null;
            oldNurse.removeMedicalReport(this);
         }
         if (newNurse != null)
         {
            this.nurse = newNurse;
            this.nurse.addMedicalReport(this);
         }
      }
   }

}