package com.example.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@NotNull(message="Member ID is required")
	private int memberId;
	@NotNull(message="Member Name is required")
	private String name;
	@NotNull(message="Member email is required")
	@Email(message="Email format is wrong")
    private String email;
	@NotNull(message="Phone no is required")
	@Digits(message = "Mobile number should be a number", integer = 10, fraction = 0)
    private long mobile;
	@NotNull(message="Gender is required")
	@Pattern(regexp = "^[MF]$", message="Gender should be either M(Male) or F(Female)")
    private char gender;
	@NotNull(message="Address is required")
    private String address;
}
