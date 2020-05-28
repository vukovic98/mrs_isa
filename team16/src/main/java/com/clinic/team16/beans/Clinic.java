package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;


@Entity
@Embeddable
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Clinic_ID", nullable = false)
	private long clinicID;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "Address", nullable = false)
	private String address;

	@Column(name = "Description", nullable = false)
	private String description;

	@ElementCollection
	@CollectionTable(name = "clinic_ordinations", joinColumns = @JoinColumn(name = "clinic_id"))
	public List<Ordination> ordinations;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Pricelist_ID")
	public Pricelist pricelist;

	@ElementCollection
	@CollectionTable(name = "clinic_administrators", joinColumns = @JoinColumn(name = "clinic_id"))
	public List<ClinicAdministrator> clinicAdministrators;

	@ElementCollection
	@CollectionTable(name = "clinic_doctors", joinColumns = @JoinColumn(name = "clinic_id"))
	public List<Doctor> doctors;

	@ElementCollection
	@CollectionTable(name = "clinic_nurses", joinColumns = @JoinColumn(name = "clinic_id"))
	public List<Nurse> nurses;
	
	@ElementCollection
	@CollectionTable(name = "clinic_grades", joinColumns = @JoinColumn(name = "clinic_id"))
	public List<Grade> grades;

	public Clinic() {

	}

	public Clinic(String name, String address, String description, ArrayList<Ordination> ordinations,
			Pricelist pricelist, ArrayList<ClinicAdministrator> clinicAdministrators, ArrayList<Doctor> doctors,
			ArrayList<Nurse> nurses, ArrayList<Grade> grades) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.ordinations = ordinations;
		this.pricelist = pricelist;
		this.clinicAdministrators = clinicAdministrators;
		this.doctors = doctors;
		this.nurses = nurses;
		this.grades = grades;
	}

	public long getClinicID() {
		return clinicID;
	}

	public void setClinicID(long clinicID) {
		this.clinicID = clinicID;
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

	public List<Grade> getGrades() {
		return grades;
	}
	public double getAverageGrade() {
		double avg = 0;
		if(this.grades.size() > 0) {
			for (Grade g :this.grades) {
				avg += g.getGradeNumber();
			}
			return Math.round(avg/this.grades.size());
		}
		else {
			return 0;
		}
		
	}

	public void setGrades(ArrayList<Grade> grades) {
		this.grades = grades;
	}
	
public Grade addGrade(Patient p,int grade) {	
		for(Grade g : this.grades) {
			if(g.getPatient().getEmail().equalsIgnoreCase(p.getEmail())) {
				g.setGradeNumber(grade);
				return g;				
			}
		}
		
		Grade g = new Grade(grade, p);
		this.grades.add(g);
		return g;
	}

}