package com.example.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@NotNull(message="Name is required")
	@Pattern(regexp = "^[a-zA-Z ]+$")
	String name;
	@NotNull(message="Age is required")
	@Digits(message="Age should be a number", fraction = 0, integer = 3)
	int age;
	@NotNull(message="School type is required")
	@Pattern(regexp = "cbse|ssc")
	String sType;
	@NotNull(message="Name is required")
	@Pattern(regexp = "^[a-zA-Z ]+$")
	String sname;
	@NotNull(message="Age is required")
	@Digits(message="Age should be a number", fraction = 0, integer = 3)
	int marks;
}
