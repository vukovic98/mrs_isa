package com.clinic.team16.beans.DTO;

import java.util.ArrayList;

public class MedicationsEditDTO {
	private ArrayList<String> medicationsAdd;
	private ArrayList<String> medicationsRemove;

	public MedicationsEditDTO() {
		super();
	}

	public MedicationsEditDTO(ArrayList<String> medicationsAdd, ArrayList<String> medicationsRemove) {
		super();
		this.medicationsAdd = medicationsAdd;
		this.medicationsRemove = medicationsRemove;
	}

	public ArrayList<String> getMedicationsAdd() {
		return medicationsAdd;
	}

	public void setMedicationsAdd(ArrayList<String> medicationsAdd) {
		this.medicationsAdd = medicationsAdd;
	}

	public ArrayList<String> getMedicationsRemove() {
		return medicationsRemove;
	}

	public void setMedicationsRemove(ArrayList<String> medicationsRemove) {
		this.medicationsRemove = medicationsRemove;
	}

}
