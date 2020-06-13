package com.clinic.team16.beans.DTO;

public class GraphDTO {
private String label;
private Double y;
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public Double getY() {
	return y;
}
public void setY(Double y) {
	this.y = y;
}
public GraphDTO() {
	super();
}
public GraphDTO(String label, Double y) {
	super();
	this.label = label;
	this.y = y;
}


}
