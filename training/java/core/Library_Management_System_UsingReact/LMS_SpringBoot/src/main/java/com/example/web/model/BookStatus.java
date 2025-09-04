package com.example.web.model;


public enum BookStatus {
	ACTIVE,
	INACTIVE;
	
	 public char getCode() {
	     return this.name().charAt(0);
	 }
}
