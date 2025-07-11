package com.app.enums;

public enum Status {
	ACTIVE("A"),INACTIVE("I");
	
	private String status;
	Status(String status)
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
