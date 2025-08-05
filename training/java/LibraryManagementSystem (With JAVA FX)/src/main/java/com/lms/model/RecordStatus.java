package com.lms.model;

public enum RecordStatus {
	Issued,
	Returned;
	
	 public char getCode() {
	     return this.name().charAt(0);
	 }
}
