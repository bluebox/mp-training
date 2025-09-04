package com.lms.model;

public enum BookAvailability {
	AVAILABLE,
	ISSUED;
	
	public char getCode() {
	     return this.name().charAt(0);
	 }
	
}
