 package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
 

@Entity
@Embeddable
public class Diagnosis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Diagnosis_ID", nullable = false)
	private long diagnosisId;
	
	@Column(name = "Name" , nullable = false)
	private String name;
	
	@Column(name = "Code", nullable = false)
	private String code;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "clinicalCenter_ID")
	public ClinicalCenter clinicalCenter;

	public Diagnosis() {
		super();
	}

	public Diagnosis(String name, String code, ClinicalCenter clinicalCenter) {
		super();
		this.name = name;
		this.code = code;
		this.clinicalCenter = clinicalCenter;
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
				oldClinicalCenter.removeDiagnosis(this);
			}
			if (newClinicalCenter != null) {
				this.clinicalCenter = newClinicalCenter;
				this.clinicalCenter.addDiagnosis(this);
			}
		}
	}

}