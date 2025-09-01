package com.rms.restaurant_management_system_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;

public enum ItemCategory {
	STARTER("Starter"), MAIN_COURSE("Main Course"), DESSERT("Dessert"), BEVERAGE("Beverage"), SNACK("Snack"),
	SALAD("Salad"), SOUP("Soup");

	private String name;

	ItemCategory(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {

		return name;
	}

	@JsonCreator
	public static ItemCategory fromName(String name) {

		for (ItemCategory category : ItemCategory.values()) {
			if (category.getName().equalsIgnoreCase(name)) {
				return category;
			}
		}
		throw new RestaurantOperationException("Select Item Category from Options");
	}
}
