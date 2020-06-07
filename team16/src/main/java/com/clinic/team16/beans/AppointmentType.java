package com.clinic.team16.beans;

public enum AppointmentType {
	ORTHOPEDIA,
	GENERAL,
	CHEMICAL,
	PSIHOLOGY{
	    @Override
	    public String toString() {
	      return "PSIHOLOGY";
	    }
	  },
	NEUROLOGICAL,
	PLASTICS,
	CARDIO,
	SURGERY,
	GINECOLOGY,
	PULMOLOGY
}
