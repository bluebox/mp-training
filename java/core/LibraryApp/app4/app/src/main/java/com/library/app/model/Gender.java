package com.library.app.model;


public enum Gender {
	MALE("M"), FEMALE("F");

	private String val;

	Gender(String val) {
		this.val = val;
	}

	public String getval() {
		return val;
	}
}





