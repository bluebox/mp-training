package com.rms.restaurant_management_system_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;

public enum ItemStatus {
	ACTIVE("Active"), INACTIVE("Inactive");

	private String name;

	ItemStatus(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static ItemStatus fromName(String name) {

		for (ItemStatus status : ItemStatus.values()) {
			if (status.name.equalsIgnoreCase(name)) {
				return status;
			}
		}
		throw new RestaurantOperationException("Select Item Status from Options");
	}
}
