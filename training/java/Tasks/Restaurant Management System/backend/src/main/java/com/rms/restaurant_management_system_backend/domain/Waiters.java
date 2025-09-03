package com.rms.restaurant_management_system_backend.domain;

import com.rms.restaurant_management_system_backend.constant.WaiterAvailability;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Waiters {
	private int waiterId;
	
	@NotNull(message="Employee id bcannot be null")
	private int EmployeeId;
	
	@NotNull(message="Availability cannot be null")
	private WaiterAvailability availability;
}
