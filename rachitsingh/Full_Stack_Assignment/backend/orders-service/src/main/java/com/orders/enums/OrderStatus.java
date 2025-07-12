package com.orders.enums;

public enum OrderStatus {
	DELIVERED("D", "Delivered"), CANCELLED("C", "Cancelled"), PROCESSING("P", "Processing");

	private final String code;
	private final String label;

	OrderStatus(String code, String label) {
		this.code = code;
		this.label = label;
	}

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
}
