package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.OrdinationType;

public class OrdinationDTO {
private String name;
private OrdinationType type;
private int ordId;
private boolean hasAppointments;
private String nextTerm;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public OrdinationType getType() {
	return type;
}
public void setType(OrdinationType type) {
	this.type = type;
}
public OrdinationDTO() {
	super();
}
public OrdinationDTO(String name, OrdinationType type) {
	super();
	this.name = name;
	this.type = type;
}
public int getOrdId() {
	return ordId;
}
public void setOrdId(int ordId) {
	this.ordId = ordId;
}
public OrdinationDTO(String name, OrdinationType type, int ordId) {
	super();
	this.name = name;
	this.type = type;
	this.ordId = ordId;
}
public boolean isHasAppointments() {
	return hasAppointments;
}
public void setHasAppointments(boolean hasAppointments) {
	this.hasAppointments = hasAppointments;
}
public OrdinationDTO(String name, OrdinationType type, int ordId, String nextTerm) {
	super();
	this.name = name;
	this.type = type;
	this.ordId = ordId;
	this.nextTerm = nextTerm;
}
public String getNextTerm() {
	return nextTerm;
}
public void setNextTerm(String nextTerm) {
	this.nextTerm = nextTerm;
}






}
