package com.example.spring.model;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
	private String name;
    
    @NotNull(message = "Age is required")
    @Range(min=1 ,max = 100, message = "Age must be between 1 and 100")
	private Integer age;
    
    @NotBlank(message="Pnum must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String pnum;
    
    @NotBlank(message="email must not be blank")
    @Email(message = "Please provide a valid email address" )
	private String email;

}
