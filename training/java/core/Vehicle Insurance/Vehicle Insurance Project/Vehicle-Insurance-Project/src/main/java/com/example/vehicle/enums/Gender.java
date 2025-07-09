package com.example.vehicle.enums;

public enum Gender {
	MALE("M"), FEMALE("F");

	private String val;

	Gender(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
