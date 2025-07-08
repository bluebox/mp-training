package com.example.vehicle.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {
	@NotBlank(message = "Username must not be Null")
	private String userName;
	@NotBlank(message = "password must not be Null")
	private String password;
	private String passwordUpdatedBy;
	private LocalDateTime passwordUpdatedOn;
	private int customerId;
}