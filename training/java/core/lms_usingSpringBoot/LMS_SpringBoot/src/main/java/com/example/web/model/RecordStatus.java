package com.example.web.model;

public enum RecordStatus {
	Issued,
	Returned;
	
	 public String getCode() {
	     return String.valueOf(this.name().charAt(0));
	 }
}
