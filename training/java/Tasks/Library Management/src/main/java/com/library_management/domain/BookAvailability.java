package com.library_management.domain;

import java.util.stream.Stream;

public enum BookAvailability {
//	Defining all the status constants
	AVAILABLE("Available"),
	ISSUED("Issued");
	
	private String displayName;
	
//	Constructor
	BookAvailability(String displayName){
		this.displayName=displayName;
	}
	
//	Defining all the related functions
	public String getStringValue() {
		return this.displayName.substring(0,1);
	}
	
	@Override
	public String toString() {
		return this.displayName;
	}
	
	public static BookAvailability getEnumConstant(String value) {
	    return Stream.of(BookAvailability.values())
	        .filter(e -> e.getStringValue().equals(value))
	        .findFirst()
	        .orElse(null);
	}
}
