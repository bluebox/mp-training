package com.example.springbootexample.demospringboot;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Component
@Data
public class Business {

	@NotBlank(message="businessname should not be empty")
    //@Size(min=3, message="Name must be at least 3 characters long")
	private String businessname;
	
	//@NotBlank(message="businessstatergy should not be empty")
	private String businessstatergy;
	
	//@NotBlank(message="businessshop should not be empty")
	private String businessshop;
	
	//@NotNull(message="businessid should not be empty")
	private int businessid;
	
	
	@NotNull(message="phone no should not be null")
	private long phoneno;
	
	@Email(message="invalid email ")
	private String email;
	
	
	
	
}
