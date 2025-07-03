package com.library.app.model;

public enum Status {
	ACTIVE("A"), 
	INACTIVE("I");

	private String val;

	Status(String val) {
		this.val = val;
	}

	public String getval() {
		return val;
	}
}


