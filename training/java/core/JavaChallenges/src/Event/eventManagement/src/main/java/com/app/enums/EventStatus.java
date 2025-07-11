package com.app.enums;

public enum EventStatus {
	
	ACTIVE("A"),
	FINISHED("F"),
	CANCELLED("C");
	private String status;
	EventStatus(String status){
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}