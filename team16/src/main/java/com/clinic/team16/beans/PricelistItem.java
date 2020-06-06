package com.clinic.team16.beans;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Embeddable
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PricelistItem_ID", nullable = false)
	private long pricelistItemId;

	public long getPricelistItemId() {
		return pricelistItemId;
	}

	@Enumerated(EnumType.STRING)
	private AppointmentType appointmentType;

	@Column(name = "price", nullable = false)
	private double price;

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Pricelist_ID")
	public Pricelist pricelist;

	public PricelistItem() {
		super();
	}

	public PricelistItem(AppointmentType appointmentType, double price, Pricelist pricelist) {
		super();
		this.appointmentType = appointmentType;
		this.price = price;
		this.pricelist = pricelist;
	}

	public AppointmentType getName() {
		return appointmentType;
	}

	public void setName(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist newPricelist) {
		this.pricelist = newPricelist;
	}

}