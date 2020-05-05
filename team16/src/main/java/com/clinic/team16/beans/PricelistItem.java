package com.clinic.team16.beans;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PricelistItem_ID", nullable = false)
	private long pricelistItemId;

	@Column(name = "name", nullable = false)
	private String appointmentType;

	@Column(name = "price", nullable = false)
	private double price;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "Pricelist_ID")
	public Pricelist pricelist;

	public PricelistItem() {
		super();
	}

	public PricelistItem(String appointmentType, double price, Pricelist pricelist) {
		super();
		this.appointmentType = appointmentType;
		this.price = price;
		this.pricelist = pricelist;
	}

	public String getName() {
		return appointmentType;
	}

	public void setName(String appointmentType) {
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