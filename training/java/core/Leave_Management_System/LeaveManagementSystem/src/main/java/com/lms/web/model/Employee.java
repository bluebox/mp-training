package com.lms.web.model;


import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Positive(message = "Employee ID must be a positive number.")
    private int employeeId;

    @NotBlank(message = "Employee name cannot be Empty.")
    @Size(max = 255, message = "Employee name cannot exceed 255 characters.")
    private String employeeName;

    @NotBlank(message = "Employee email cannot be Empty.")
    @Email(message = "Employee email must be a valid email address.")
    @Size(max = 255, message = "Employee email cannot exceed 255 characters.")
    private String employeeEmail;

    @NotBlank(message = "Employee role cannot be Empty.")
    @Pattern(regexp = "^(ADMIN|MANAGER|EMPLOYEE)$", message = "Employee role must be ADMIN, MANAGER, or EMPLOYEE.")
    private String employeeRole;
    
    @NotBlank(message="Employee Mobile Number cannot be Empty.")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Please enter a valid 10 digits phone number")
    private String employeeMobileNo;
    
    @NotBlank(message="Date_Of_Birth cannot be Empty.")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate employeeDOB;

    @NotBlank(message="Employee Joining Date cannot be Empty.")
    @PastOrPresent(message = "Joining Date must be in the past or present.")
    private LocalDate employeeDOJ;

    @NotBlank(message = "Employee Gender cannot be Empty")
	@Pattern(regexp = "^[MF]$",message="Please select Your gender Correctly")
    private String employeeGender;

	@NotBlank(message = "Employee Address cannot be Empty.")
    private String employeeAddress;


    @Positive(message = "Reporter ID must be a positive number if provided.")
    private Integer reporterId;
}

