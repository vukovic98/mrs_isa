package com.clinic.team16.beans;

import java.util.*;

public class AppointmentType {

	private double discount;
	public PricelistItem pricelistItem;

	public AppointmentType() {

	}

	public AppointmentType(double discount, PricelistItem pricelistItem) {
		super();
		this.discount = discount;
		this.pricelistItem = pricelistItem;
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

}