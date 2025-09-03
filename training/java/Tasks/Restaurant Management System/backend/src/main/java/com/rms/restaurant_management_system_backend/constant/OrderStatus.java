package com.rms.restaurant_management_system_backend.constant;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
	PENDING("Pending"), CANCELLED("Cancelled"), COMPLETED("Completed");

	private String status;

	OrderStatus(String status) {
		this.status = status;
	}

	@JsonValue
	public String getStatus() {
		return this.status;
	}

	@JsonCreator
	public static OrderStatus getEnumConstant(String value) {
		return Stream.of(OrderStatus.values()).filter(e -> e.getStatus().equals(value)).findFirst().orElse(null);
	}
}
