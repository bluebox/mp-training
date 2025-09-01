package com.rms.restaurant_management_system_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;

public enum ItemAvailability {

	AVAILABLE("Available"), UNAVAILABLE("Unavailable");

	private String name;

	ItemAvailability(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static ItemAvailability fromName(String name) {

		for (ItemAvailability availability : ItemAvailability.values()) {
			if (availability.name.equalsIgnoreCase(name)) {
				return availability;
			}
		}
		throw new RestaurantOperationException("Select Item Availability from Options");
	}

}
