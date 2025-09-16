package com.users.Users.model;

import java.time.LocalDateTime;

import com.users.Users.enums.Gender;
import com.users.Users.enums.UserStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	
    
//    @NotBlank(message = "Username cannot be blank")
//	@Size(min = 5, max = 15, message = "Username should contain atleast 5 chars and atmost 15 chars")
    private String username;
    

    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", 
             message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Firstname cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-'][a-zA-Z]+)*$", message = "Firstname should contain only chars")
    private String firstName;
    
    @NotBlank(message = "Lastname cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-'][a-zA-Z]+)*$", message = "Lastname should contain only chars")
    private String lastName;
    
    private Gender gender;
    
    @NotBlank(message = "Phonenumber cannot be blank")
    @Pattern(regexp = "^[1-9][0-9]{9}$", message = "Phone number should be a 10 digits.")
    private String phoneNumber;
    
    @NotBlank(message = "Please Select a Country.")
    private String country;
    
    @NotBlank(message = "Please Select a State.")
    private String state;
    
    @NotBlank(message = "Please Select a City.")
    private String city;
    
    @NotBlank(message = "PostalCode cannot be blank")
    @Pattern(regexp = "^[0-9]{6}$", message = "Postal code should be a 6 digits number.")
    private String postalCode;
    
    private UserStatus status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    
}
