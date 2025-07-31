package com.medplus.marketing.domain;

public enum StatusCode {

	SUCCESS("SUCCESS"), FAILURE("FAILURE");
	
	public final String message;
	
	StatusCode(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
