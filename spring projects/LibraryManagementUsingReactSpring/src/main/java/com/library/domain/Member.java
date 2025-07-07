package com.library.domain;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Member {
    private int memberId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "[A-Za-z ]+", message = "Name must contain only alphabets and spaces")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotNull(message = "Mobile number is required")
    @Digits(integer = 10, fraction = 0, message = "Mobile number must be exactly 10 digits")
    @Min(value = 1000000000L, message = "Mobile number must be 10 digits")
    private Long mobile;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Size(max = 200, message = "Address must not exceed 200 characters")
    private String address;
    
    public Member() {}

	public Member(
			@NotBlank(message = "Name is required") @Pattern(regexp = "[A-Za-z ]+", message = "Name must contain only alphabets and spaces") @Size(max = 50, message = "Name must not exceed 50 characters") String name,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 100, message = "Email must not exceed 100 characters") String email,
			@NotNull(message = "Mobile number is required") @Digits(integer = 10, fraction = 0, message = "Mobile number must be exactly 10 digits") @Min(value = 1000000000, message = "Mobile number must be 10 digits") Long mobile,
			@NotNull(message = "Gender is required") Gender gender,
			@Size(max = 200, message = "Address must not exceed 200 characters") String address) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.address = address;
	}
    
    
}
