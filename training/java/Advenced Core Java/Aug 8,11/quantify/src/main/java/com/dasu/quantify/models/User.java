package com.dasu.quantify.models;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class User {
	
	@NotBlank(message = "Name should never be blank")
	String name;
	Long phone;
}