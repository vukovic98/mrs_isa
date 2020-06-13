package com.clinic.team16.beans.DTO;

import java.util.ArrayList;

import com.clinic.team16.beans.Allergies;

public class AllergiestEditDTO {
	private ArrayList<Allergies> allergiesAdd;
	private ArrayList<Allergies> allergiesRemove;

	public AllergiestEditDTO() {
		super();
	}

	public AllergiestEditDTO(ArrayList<Allergies> allergiesAdd, ArrayList<Allergies> allergiesRemove) {
		super();
		this.allergiesAdd = allergiesAdd;
		this.allergiesRemove = allergiesRemove;
	}

	public ArrayList<Allergies> getAllergiesAdd() {
		return allergiesAdd;
	}

	public void setAllergiesAdd(ArrayList<Allergies> allergiesAdd) {
		this.allergiesAdd = allergiesAdd;
	}

	public ArrayList<Allergies> getAllergiesRemove() {
		return allergiesRemove;
	}

	public void setAllergiesRemove(ArrayList<Allergies> allergiesRemove) {
		this.allergiesRemove = allergiesRemove;
	}

}
