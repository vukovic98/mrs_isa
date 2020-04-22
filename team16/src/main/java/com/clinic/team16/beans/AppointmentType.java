
/***********************************************************************
 * Module:  AppointmentType.java
 * Author:  Vladimir
 * Purpose: Defines the Class AppointmentType
 ***********************************************************************/

import java.util.*;

public class AppointmentType {

	private double discount;
	public PricelistItem pricelistItem;
	public ArrayList<Appointment> appointments;

	public AppointmentType() {

	}

	public AppointmentType(double discount, PricelistItem pricelistItem, ArrayList<Appointment> appointments) {
		super();
		this.discount = discount;
		this.pricelistItem = pricelistItem;
		this.appointments = appointments;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public PricelistItem getPricelistItem() {
		return pricelistItem;
	}

	public void setPricelistItem(PricelistItem pricelistItem) {
		this.pricelistItem = pricelistItem;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

}