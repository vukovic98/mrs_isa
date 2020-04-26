package com.clinic.team16.beans;

import java.util.*;
import javax.persistence.*;

@Entity
@Embeddable
public class Medication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Medication_ID", nullable = false)
	private long medicationId;
	
	@Column(name = "Name", nullable = false)
	private String name;
	
	@Column(name = "Code", nullable = false)
	private String code;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Medication_ClinicalCenter_ID")
	public ClinicalCenter clinicalCenter;

	public Medication(String name, String code, ClinicalCenter clinicalCenter) {
		super();
		this.name = name;
		this.code = code;
		this.clinicalCenter = clinicalCenter;
	}

	public Medication() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ClinicalCenter getClinicalCenter() {
		return clinicalCenter;
	}

	public void setClinicalCenter(ClinicalCenter newClinicalCenter) {
		if (this.clinicalCenter == null || !this.clinicalCenter.equals(newClinicalCenter)) {
			if (this.clinicalCenter != null) {
				ClinicalCenter oldClinicalCenter = this.clinicalCenter;
				this.clinicalCenter = null;
				oldClinicalCenter.removeMedication(this);
			}
			if (newClinicalCenter != null) {
				this.clinicalCenter = newClinicalCenter;
				this.clinicalCenter.addMedication(this);
			}
		}
	}

}