package com.EventManagement.EMS_Backend.Model;


import java.util.HashMap;
import java.util.Map;

public enum EventStatus {

	ACTIVE("active"), INACTIVE("inactive");

	String string;

	EventStatus(String string) {
		this.string = string;
	}

	public String getType() {
		return this.string;
	}

	private static final Map<String, EventStatus> lookup = new HashMap<>();

	static {
		for (EventStatus eventStatus : EventStatus.values()) {
			lookup.put(eventStatus.getType(), eventStatus);
		}
	}
	public static EventStatus getEventStatus(String Status) {
		return lookup.get(Status);
	}

}