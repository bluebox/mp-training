package com.EventManagement.EMS_Backend.Model;



import java.util.HashMap;
import java.util.Map;

public enum EventApproveStatus {

	PENDING("pending"), APPROVED("approved"),CANCELLED("cancelled");

	String string;

	EventApproveStatus(String string) {
		this.string = string;
	}

	public String getType() {
		return this.string;
	}

	private static final Map<String, EventApproveStatus> lookup = new HashMap<>();

	static {
		for (EventApproveStatus eventApproveStatus : EventApproveStatus.values()) {
			lookup.put(eventApproveStatus.getType(), eventApproveStatus);
		}
	}
	public static EventApproveStatus getEventApproveStatus(String Status) {
		return lookup.get(Status);
	}

}