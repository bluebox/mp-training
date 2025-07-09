package com.example.vehicle.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.vehicle.enums.PolicyStatus;

import lombok.Data;

@Data
public class Policy {
	private int policyId;
	@NotNull(message = "Policy term is required")
	@Min(message = "Policy term should be greater than or equal to 1", value = 1)
	private int policyTerm;
	@NotBlank(message = "Policy type is required")
	private String policyType;
	private double premiumAmount;
	private double policyAmount;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private PolicyStatus policyStatus;
	@NotNull(message = "Vehicle ID is required")
	private int vehicleId;
	@NotBlank(message = "It is nessessary to know who approved it")
	private String approvedBy;

	
}