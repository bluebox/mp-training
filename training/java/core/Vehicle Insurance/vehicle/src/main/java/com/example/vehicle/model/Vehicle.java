package com.example.vehicle.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class Vehicle {
	private int vehicleId;
	@NotBlank(message="Chasis Number must not be null")
	private String chasisNum;
	@NotBlank(message="Registration Number must not be null")
	private String regNum;
	@NotBlank(message="Vehicle model must not be null")
	private String vehicleModel;
	@NotNull(message="Purchase Date must not be null")
	private LocalDateTime purchaseDate;
	private LocalDateTime  vehicleUpdatedOn;
	private String vehicleUpdatedBy;
	private char status;
	private int customerId;
	private String createdBy;
}
