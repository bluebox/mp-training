package com.example.demo.validation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
	
	@NotNull
	@Size(max=64)
	private String name;
	
	@Min(0)
	private Integer age;
	
	private String email;
}
