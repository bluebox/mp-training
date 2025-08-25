package com.EventManagement.EMS_Backend.Model;



import java.util.HashMap;
import java.util.Map;

public enum EventCategory {
	GAMES("games"), WORKSHOPS("workshops"), SEMINARS("seminars"),CULTURALS("cultures");
	String string;
	EventCategory(String string) {
		this.string = string;
	}

	public String getType() {
		return this.string;
	}

	private static final Map<String, EventCategory> lookup = new HashMap<>();

	static {
		for (EventCategory eventCategory : EventCategory.values()) {
			lookup.put(eventCategory.getType(), eventCategory);
		}
	}

	public static EventCategory getEventCategorys(String Status) {
		return lookup.get(Status);
	}

}