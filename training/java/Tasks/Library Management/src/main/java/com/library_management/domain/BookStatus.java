package com.library_management.domain;

import java.util.stream.Stream;

public enum BookStatus {
//	Defining all the status constants
	ACTIVE("Active"),
	INACTIVE("Inactive");
	
	private String displayName;
	
//	Constructor
	BookStatus(String displayName){
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
	
	public static BookStatus getEnumConstant(String value) {
	    return Stream.of(BookStatus.values())
	        .filter(e -> e.getStringValue().equals(value))
	        .findFirst()
	        .orElse(null);
	}
}
