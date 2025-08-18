package com.SpringBoot_LMS.SpringBoot_LMS.model;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
     MALE("M"),
     FEMALE("F");
    String string;
	
	Gender(String string) {
		this.string=string;
	}
	
	 public String getType() {
	        return this.string;
	    } 
	
	
private static final Map<String,Gender> lookup=new HashMap<>();
	
	static {
		for(Gender gender:Gender.values()) {
			lookup.put(gender.getType(),gender);
		}
	}
	
	public static Gender getGender(String c) {
		return lookup.get(c);
	}
	
}
