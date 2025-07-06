package com.befit.app.beans;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberShip {
	private int id;

	@Size(min = 3, max = 50, message = "Name should be in length of 3 to 50")
	@NotBlank
	private String membershipName;

	@NotNull(message = "Cost cannot be null")
	@Positive(message = "Cost must be positive")
	@DecimalMin(value = "0.01", message = "Cost must be at least 0.01")
	@Digits(integer = 6, fraction = 2, message = "Cost can have up to 6 digits and 2 decimal places")
	private float cost;

	@NotNull(message = "Start date cannot be null")
	@PastOrPresent(message = "Start date must be in the past or present")
	private Date startDate;

	@NotNull(message = "End date cannot be null")
	@FutureOrPresent(message = "End date must be today or in the future")
	private Date endDate;

	@NotEmpty(message = "Activities list cannot be empty")
	@Size(min = 1, max = 20, message = "Activities list must contain between 1 and 20 items")
	private List<@NotBlank @Size(min = 2, max = 50, message = "Each activity must be between 2 and 50 characters") String> activities;

	@NotBlank(message = "Status cannot be blank")
	@Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be ACTIVE or INACTIVE")
	private String status;

	public MemberShip(String membershipName, float cost, Date startDate, Date endDate, List<String> activities,
			String status) {
		this.membershipName = membershipName;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activities = activities;
		this.status = status;
	}
}