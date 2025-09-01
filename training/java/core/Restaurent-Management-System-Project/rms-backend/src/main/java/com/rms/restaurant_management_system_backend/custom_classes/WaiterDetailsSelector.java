package com.rms.restaurant_management_system_backend.custom_classes;

import com.rms.restaurant_management_system_backend.constant.WaiterAvailability;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WaiterDetailsSelector {
	private String name;
	private int waiterId;
	private int employeeId;
	private WaiterAvailability availability;
}
