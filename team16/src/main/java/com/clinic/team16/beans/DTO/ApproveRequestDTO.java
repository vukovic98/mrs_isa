package com.clinic.team16.beans.DTO;

public class ApproveRequestDTO {
private int ordId;
private long requestId;
public int getOrdId() {
	return ordId;
}
public void setOrdId(int ordId) {
	this.ordId = ordId;
}
public long getRequestId() {
	return requestId;
}
public void setRequestId(long requestId) {
	this.requestId = requestId;
}
public ApproveRequestDTO(int ordId, long requestId) {
	super();
	this.ordId = ordId;
	this.requestId = requestId;
}


}
