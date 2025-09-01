package com.rms.restaurant_management_system_backend.constant;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WaiterAvailability {
	AVAILABLE("Available"), BUSY("Busy");

	private String dbName;

	WaiterAvailability(String dbName) {
		this.dbName = dbName;
	}

	@JsonValue
	public String getDbName() {
		return this.dbName;
	}

	@JsonCreator
	public static WaiterAvailability fromDbName(String dbName) {
		return Stream.of(WaiterAvailability.values()).filter(e -> e.dbName.equals(dbName)).findFirst().orElse(null);
	}
}
