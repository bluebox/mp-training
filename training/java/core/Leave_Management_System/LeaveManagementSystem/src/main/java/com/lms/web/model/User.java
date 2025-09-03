package com.lms.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int employeeId;
	
	@NotBlank(message = "User name cannot be Empty.")
    @Size(max = 255, message = "User name cannot exceed 255 characters.")
    private String userEmail;
	
	@NotBlank(message = "Password cannot be blank.")
    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters.")
    private String userPassword;
	
	@NotBlank(message = "User role cannot be Empty.")
    @Pattern(regexp = "^(ADMIN|MANAGER|EMPLOYEE)$", message = "User role must be ADMIN, MANAGER, or EMPLOYEE.")
    private String userRole;
}