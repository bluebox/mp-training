package com.springboot_practice.logging_formdata_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Holiday {
	private final String day;
	private final String reason;
	private final Type type;

	public enum Type {
		FESTIVAL, FEDERAL
	}

	public Holiday(String day, String reason, Holiday.Type type) {
    	this.day=day;
    	this.reason=reason;
    	this.type=type;
    }

	public String getDay() {
		return day;
	}

	public String getReason() {
		return reason;
	}

	public Type getType() {
		return type;
	}
	
	
}
