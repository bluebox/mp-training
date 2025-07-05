package com.befit.app.beans;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;



@Component
@Data
@AllArgsConstructor
public class Member {
	
	private int id;
	@Size(min=3,max=50 , message="Name should be in length of 3 to 50")
	@NotEmpty
	@NotNull
	@NotBlank
	private String name;
	@Min(value = 1000000000L ,message="it should contain exactly 10 digits")
	@Max(value = 9999999999L ,message="it should contain exactly 10 digits")
	private long mobile;
	@Positive(message="Age Must be Positive")
    @Max(value = 120, message = "age should be less than 120")
    @NotNull(message="age cannot be null")
	private int age;
    @DecimalMin(value = "1.0", message = "Weight must be greater than 1.0 kg")
    @DecimalMax(value = "500.0", message = "Weight must be less than 500.0 kg")
    @Digits(integer = 3, fraction = 2, message = "Weight can have up to 3 digits and 2 decimal places")
    private float weight;

    @DecimalMin(value = "0.5", message = "Height must be greater than 0.5 meters")
    @DecimalMax(value = "3.0", message = "Height must be less than 3.0 meters")
    @Digits(integer = 1, fraction = 2, message = "Height can have up to 1 digit and 2 decimal places")
    private float height;
	@Size(min=3,max=50 , message="Name should be in length of 3 to 50")
	@NotEmpty
	@NotNull
	@NotBlank
	private String address;
	private String memberShip;
	@NotNull(message = "joinDate cannot be null")
    @PastOrPresent(message = "joinDate must be in the past or present")
	private Date joinDate;
	@NotNull(message = "joinDate cannot be null")
	@FutureOrPresent(message = "The expiry date must be today or in the future")
	private Date expiryDate;
	private String status;

}
