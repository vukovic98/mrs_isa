package com.clinic.team16.beans;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
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

		this.clinicalCenter = newClinicalCenter;

	}

}