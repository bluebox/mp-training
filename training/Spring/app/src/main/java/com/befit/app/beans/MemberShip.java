package com.befit.app.beans;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
public class MemberShip {
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be positive")
    private int id;

    @NotNull(message = "Membership name cannot be null")
    @Positive(message = "Membership name must be positive")
    private int membershipName; // Assuming this is an ID; see note below for String alternative

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
    private List<@NotBlank @Size(min = 2, max = 50, message = "Each activity must be between 2 and 50 characters") String> activites;

    @NotNull(message = "Status cannot be null")
    @NotBlank(message = "Status cannot be blank")
    @Size(min = 3, max = 20, message = "Status must be between 3 and 20 characters")
    private String status; // Renamed from Status for consistency
}
