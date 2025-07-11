package com.app.enums;

public enum RegistrationStatus {
	ATTENDED("A"),REGISTERED("R"),CANCELLED("C"),NOTATTENDED("N");
	private String status;
	RegistrationStatus(String status)
	{
		this.status=status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
