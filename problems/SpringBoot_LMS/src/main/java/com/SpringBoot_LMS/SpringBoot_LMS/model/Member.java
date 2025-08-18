package com.SpringBoot_LMS.SpringBoot_LMS.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Component
@Data
public class Member {
	 @NotNull(message="id must not be null")
	private int MemberId;
	 @NotBlank(message="name must not be empty and invalid")
	    @Pattern(regexp="^[a-zA-Z]*$",message="Title must be within these [a-zA-Z]")
	   private String Name;
	 @jakarta.validation.constraints.Email(message="The emil should be valid")
	   private String Email;
	 @NotBlank(message="mobile must not be empty and 10 digits")
	    @Pattern(regexp="^[0-9]{10}*$",message="Title must be within these [0-9]")
	  private  String Mobile;
	 @NotNull(message="gender should be mentioned")
	   private Gender gender;
	 @NotBlank(message="The Address is Compulsory")
	 private   String Address;
}
