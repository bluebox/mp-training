package com.library.app.model;



public enum Availability {
	AVAILABLE("A"), 
	ISSUED("I");

	private String val;

	Availability(String val) {
		this.val = val;
	}

	public String getval() {
		return val;
	}
}

