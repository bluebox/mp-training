package com.medplus.exptracker.Model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private Integer id;
	
	@NotNull(message = "User name should not be empty.")
	private String username;
	
	@NotNull(message = "User password should not be empty")
	private String password;
	
	@NotNull(message = "Roll id should not be empty.")
	private Integer role_id;
	
	private Integer manager_id;
}
