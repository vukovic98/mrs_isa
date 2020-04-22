
/***********************************************************************
 * Module:  Clinic.java
 * Author:  Vladimir
 * Purpose: Defines the Class Clinic
 ***********************************************************************/
package com.clinic.team16.beans;
import java.util.*;

public class Clinic {

	private String name;
	private String address;
	private String description;
	public ArrayList<Ordination> ordinations;
	public Pricelist pricelist;
	public ArrayList<ClinicAdministrator> clinicAdministrators;
	public ArrayList<Doctor> doctors;
	public ArrayList<Nurse> nurses;

	public Clinic() {

	}

	public Clinic(String name, String address, String description, ArrayList<Ordination> ordinations,
			Pricelist pricelist, ArrayList<ClinicAdministrator> clinicAdministrators, ArrayList<Doctor> doctors,
			ArrayList<Nurse> nurses) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.ordinations = ordinations;
		this.pricelist = pricelist;
		this.clinicAdministrators = clinicAdministrators;
		this.doctors = doctors;
		this.nurses = nurses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	public ArrayList<ClinicAdministrator> getClinicAdministrators() {
		return clinicAdministrators;
	}

	public void setClinicAdministrators(ArrayList<ClinicAdministrator> clinicAdministrators) {
		this.clinicAdministrators = clinicAdministrators;
	}

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public ArrayList<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(ArrayList<Nurse> nurses) {
		this.nurses = nurses;
	}

	public void setOrdinations(ArrayList<Ordination> ordinations) {
		this.ordinations = ordinations;
	}

	public ArrayList<Ordination> getOrdinations() {
		if (ordinations == null)
			ordinations = new ArrayList<Ordination>();
		return ordinations;
	}

	public java.util.Iterator getIteratorOrdinations() {
		if (ordinations == null)
			ordinations = new ArrayList<Ordination>();
		return ordinations.iterator();
	}

	public void setOrdination(ArrayList<Ordination> newOrdination) {
		removeAllOrdinations();
		for (java.util.Iterator iter = newOrdination.iterator(); iter.hasNext();)
			addOrdination((Ordination) iter.next());
	}

	public void addOrdination(Ordination newOrdination) {
		if (newOrdination == null)
			return;
		if (this.ordinations == null)
			this.ordinations = new ArrayList<Ordination>();
		if (!this.ordinations.contains(newOrdination)) {
			this.ordinations.add(newOrdination);
			newOrdination.setClinic(this);
		}
	}

	public void removeOrdination(Ordination oldOrdination) {
		if (oldOrdination == null)
			return;
		if (this.ordinations != null)
			if (this.ordinations.contains(oldOrdination)) {
				this.ordinations.remove(oldOrdination);
				oldOrdination.setClinic((Clinic) null);
			}
	}

	public void removeAllOrdination() {
		if (ordinations != null) {
			Ordination oldOrdination;
			for (java.util.Iterator iter = getIteratorOrdinations(); iter.hasNext();) {
				oldOrdination = (Ordination) iter.next();
				iter.remove();
				oldOrdination.setClinic((Clinic) null);
			}
		}
	}

}