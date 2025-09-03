package com.sms.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    private Long supplierId;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String name;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "M|F", message = "Gender must be either 'M' (Male) or 'F' (Female)")
    private String gender;

    @NotNull(message = "Mobile number is required")
    @Digits(integer = 10, fraction = 0, message = "Mobile number must be exactly 10 digits with no letters or symbols")
    @Min(value = 1000000000L, message = "Mobile number must be exactly 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must be exactly 10 digits")
    private Long mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must be at most 50 characters")
    private String country;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State must be at most 50 characters")
    private String state;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City must be at most 50 characters")
    private String city;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be at most 200 characters")
    private String address;

    private Timestamp createdAt;

    @NotBlank(message = "Created By is required")
    @Size(max = 50, message = "Created By must be at most 50 characters")
    private String createdBy;
}
