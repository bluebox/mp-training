package com.EventManagement.EMS_Backend.Model;



import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Email;


@Data
public class ModelUser {
	
	
	//@NotNull(message="userid should not be null")
	private String UserId;
	@NotNull(message="username should not be null")
	@NotBlank(message="username should not be Blank")
	private String UserName;
	@NotNull(message="email should not be null")
	@Email
	private String Email;
	@NotNull(message="mobileno should not be null")
	@NotBlank(message="mobileno should not be Blank")
	private String MobileNumber;
	@NotNull(message="password should not be null")
	@NotBlank(message="password should not be Blank")
	private String Password;
	@NotNull
	private UserEnum Usertype;
}