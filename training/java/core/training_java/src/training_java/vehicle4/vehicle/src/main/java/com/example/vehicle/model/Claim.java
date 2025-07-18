package com.example.vehicle.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.vehicle.enums.ClaimStatus;

import lombok.Data;


@Data
public class Claim {
	private int claimId;
	@NotNull(message = "Please tell us howmuch amount you want to claim")
	private double reqAmount;
	@NotBlank(message="Please mention the type of damage taken place")
	private String damageType;
	private ClaimStatus claimStatus;
	private LocalDateTime claimDate;
	@NotNull(message = "Policy ID is required")
	private int policyId;
	private String approvedBy;
	
}
