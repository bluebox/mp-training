package com.example.web.model;

public enum BookAvailability {
	AVAILABLE,
	ISSUED;
	
	public char getCode() {
	     return this.name().charAt(0);
	 }
	
}
