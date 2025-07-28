package com.app.model;

import com.app.enums.Gender;
import com.app.enums.UserStatus;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class User {

    @Min(value = 1, message = "User ID must be greater than or equal to 1")
    private int userId;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phnNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;


    @NotBlank(message = "Role cannot be empty")
    @Pattern(regexp = "^(ADMIN|USER|FACULTY)$", message = "Role must be one of the following: ADMIN, USER, FACULTY")
    private String role;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @NotNull(message = "User status cannot be null")
    private UserStatus status;

    @Size(max = 100, message = "Department name should not exceed 100 characters")
    private String dept;

}
