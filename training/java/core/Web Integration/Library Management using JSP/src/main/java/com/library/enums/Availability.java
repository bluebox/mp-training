package com.library.enums;

public enum Availability {
	
	
	Available('A'),Issued('I');

	private char c;

	Availability(char c) {
		this.c = c;
	}
	public char getAvailability() {
		return c;
	}
	public void setAvailability(char c) {
		this.c=c;
	}
}