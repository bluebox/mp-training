package com.medplus.exptracker.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Integer id;
	
	@NotNull(message = "User name should not be empty.")
	private String username;
	
	@NotNull(message = "Roll id should not be empty.")
	private Integer roleId;
	
	private Integer managerId;
}