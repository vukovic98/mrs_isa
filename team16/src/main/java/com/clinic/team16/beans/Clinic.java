package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;
@Entity
@Embeddable
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Clinic_ID",nullable = false)
	private long clinicID;
	
	@Column(name = "Name",nullable = false)
	private String name;
	
	@Column(name = "Address",nullable = false)
	private String address;
	
	@Column(name = "Description",nullable = false)
	private String description;
	
	@ElementCollection
	@CollectionTable(name = "clinic_ordinations",joinColumns = @JoinColumn(name = "clinic_id"))
	public List<Ordination> ordinations;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Pricelist_ID")
	public Pricelist pricelist;
	
	@ElementCollection
	@CollectionTable(name = "clinic_administrators",joinColumns = @JoinColumn(name = "clinic_id"))
	public List<ClinicAdministrator> clinicAdministrators;
	
	@ElementCollection
	@CollectionTable(name = "clinic_doctors",joinColumns = @JoinColumn(name = "clinic_id"))
	public List<Doctor> doctors;
	
	@ElementCollection
	@CollectionTable(name = "clinic_nurses",joinColumns = @JoinColumn(name = "clinic_id"))
	public List<Nurse> nurses;

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

	public List<ClinicAdministrator> getClinicAdministrators() {
		return clinicAdministrators;
	}

	public void setClinicAdministrators(ArrayList<ClinicAdministrator> clinicAdministrators) {
		this.clinicAdministrators = clinicAdministrators;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(ArrayList<Nurse> nurses) {
		this.nurses = nurses;
	}

	public void setOrdinations(ArrayList<Ordination> ordinations) {
		this.ordinations = ordinations;
	}

	public List<Ordination> getOrdinations() {
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

	public void removeAllOrdinations() {
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