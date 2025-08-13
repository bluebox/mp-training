package com.springboot.applicationspringboot.Model;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

import org.springframework.stereotype.Component;

@Data
@Component
public class Teacher {

	@NotBlank(message="Name must not be blank")
	@Size(min=3, message="Name must be at least 3 characters long")
	private String Name;
//	@NotNull(message="Id must not be null")
//	@Pattern(regexp = "^[0-9]{10,20}",message="Id must be Numeric Value")
//	private String ID;
//	@NotEmpty(message="experience field must be filled")
//	private String experience;
//	@NotEmpty(message="Subjects cannot be empty")
//	private String subjects;
	@NotEmpty(message="Email is Compulsory")
	@Email(message="not a valid email")
	private String Email;
//	@NotEmpty(message="Bank Details is Necessary")
//	@Size(min=10,max=20,message="Bank name should be not long")
//	private String Bank;
//	@NotNull(message="Bank Account Number is necessary")
//	@Min(1000000000)
//	private int Accno;
	
	
}
