package com.rms.restaurant_management_system_backend.constant;

import java.util.stream.Stream;

public enum Designation {

	ADMIN("Admin"), STAFF("Staff"), WAITER("Waiter");

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Designation(String name) {
		this.name = name;
	}

	public static Designation getEnumConstant(String value) {
		return Stream.of(Designation.values()).filter(e -> e.getName().equals(value)).findFirst().orElse(null);
	}

}
