package com.clinic.team16.beans;

import java.util.*;

import javax.persistence.*;
@Entity
@Embeddable
public class AppointmentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AppointmentType_ID",nullable = false)
	private long appointmentTypeID;
	
	@Column(name = "Discount",nullable = false)
	private double discount;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "PricelistItem_ID")
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