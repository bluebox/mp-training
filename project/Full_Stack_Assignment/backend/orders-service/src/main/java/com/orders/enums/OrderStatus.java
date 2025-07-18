package com.orders.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
	DELIVERED("D", "Delivered"), CANCELLED("C", "Cancelled"), PROCESSING("P", "Processing");

	private final String code;
	private final String label;

	OrderStatus(String code, String label) {
		this.code = code;
		this.label = label;
	}

	@JsonValue
	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static OrderStatus fromLabel(String label) {
		for (OrderStatus status : OrderStatus.values()) {
			if (status.getLabel().equalsIgnoreCase(label)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown Order Status label: " + label);
	}

	public static OrderStatus fromCode(String code) {
		for (OrderStatus status : OrderStatus.values()) {
			if (status.getCode().equalsIgnoreCase(code)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown Order Status code: " + code);
	}

	@JsonCreator
	public static OrderStatus fromJson(String input) {
		for (OrderStatus status : OrderStatus.values()) {
			if (status.name().equalsIgnoreCase(input) || status.getCode().equalsIgnoreCase(input)
					|| status.getLabel().equalsIgnoreCase(input)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus: " + input);
	}
}
