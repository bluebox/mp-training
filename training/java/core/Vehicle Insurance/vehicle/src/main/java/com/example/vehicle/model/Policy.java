package com.example.vehicle.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.vehicle.enums.PolicyStatus;
import com.example.vehicle.enums.PolicyType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Policy {
	private int policyId;
	@NotNull(message="Policy term is required")
	@Min(message="Policy term should be greater than or equal to 1", value = 1)
	private int policyTerm;
	@NotBlank(message = "Policy type is required")
	private String policyType;
	private double premiumAmount;
	private double policyAmount;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private char policyStatus;
	@NotNull(message = "Vehicle ID is required")
	private int vehicleId;
	@NotBlank(message="It is nessessary to know who approved it")
	private String approvedBy;
	public Policy(int policyTerm, PolicyType policyType, LocalDateTime startDate,LocalDateTime endDate, PolicyStatus policyStatus, int vehicleId, String approvedBy) {
		this.policyTerm = policyTerm;
		this.policyType = policyType.getPtype();
		this.premiumAmount = policyType.getPremiumAmount();
		this.policyAmount = policyType.getPolicyAmount();
		this.startDate = startDate;
		this.endDate = endDate;
		this.policyStatus = policyStatus.getC();
		this.vehicleId = vehicleId;
		this.approvedBy = approvedBy;
	}
}
