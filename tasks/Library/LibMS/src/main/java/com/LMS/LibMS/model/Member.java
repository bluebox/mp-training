package com.LMS.LibMS.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.LMS.LibMS.model.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Id
	private Integer memberID;
	
	@NotBlank(message="Name must not be blank")
	@Size(min=5,max=20, message="Name must be at least 5 and at most 20 characters long")
    private String name;
	
	@NotBlank(message="Email must not be blank")
	@Size(max=25, message="Email must be at most 25 characters long")
	@Email(message = "Please Provide a Valid Email id")
    private String email;
	
    @NotBlank(message="Mobile must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private Long phoneNumber;
    
	@NotBlank(message="Gender must not be blank")
    private Gender gender;
	
	@NotBlank(message="Address must not be blank")
    private String address;
	
	@CreatedDate
    private LocalDateTime createdAt; 
	
	@CreatedBy
    private String createdBy;   
	
	@LastModifiedDate
    private LocalDateTime updatedAt; 
	
	@LastModifiedBy
    private String updatedBy;  
}
