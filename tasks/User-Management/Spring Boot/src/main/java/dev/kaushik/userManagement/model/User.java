package dev.kaushik.userManagement.model;

import java.time.LocalDateTime;

import dev.kaushik.userManagement.model.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"createdAt","createdBy","updatedAt","updatedBy"})
public class User {

	private String firstName;

	private String lastName;

	private String email;

	private long phoneNumber;

	private Gender gender;

	private String country;

	private String state;

	private String city;

	private int pinCode;

	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;
}
