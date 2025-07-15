package com.example.vehicle.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class User {
@NotBlank(message = "Username must not be Null")
private String username;
@NotBlank(message = "password must not be Null")
private String password;
private String passwordUpdatedBy;
private LocalDateTime passwordUpdatedOn;
@NotNull(message="CustomerId must not be Null")
private int customerId;
}
