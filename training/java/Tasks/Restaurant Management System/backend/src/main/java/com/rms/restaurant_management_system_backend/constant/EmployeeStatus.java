package com.rms.restaurant_management_system_backend.constant;

import java.util.stream.Stream;

public enum EmployeeStatus {

	ACTIVE("Active"), INACTIVE("Inactive");

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private EmployeeStatus(String name) {
		this.name = name;
	}

	public static EmployeeStatus getEnumConstant(String value) {
		return Stream.of(EmployeeStatus.values()).filter(e -> e.getName().equals(value)).findFirst().orElse(null);
	}
}
