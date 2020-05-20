package com.clinic.team16.beans.DTO;

import com.clinic.team16.beans.OrdinationType;

public class OrdinationDTO {
private String name;
private OrdinationType type;
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


}
