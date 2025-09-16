package com.LMS.LibMS.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.LMS.LibMS.model.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	private Integer memberID;
	
	@NotBlank(message="Name must not be blank")
	@Size(min=5,max=20, message="Name must be at least 5 and at most 20 characters long")
    private String name;
	
	@NotBlank(message="Email must not be blank")
	@Size(max=25, message="Email must be at most 25 characters long")
	@Email(message = "Please Provide a Valid Email id")
    private String email;
	
	@NotBlank(message = "Phone number must not be blank")
	@Size(min=10,max=10,message = "Phone number must have 10 digits")
    private Long phoneNumber;
    
    private Gender gender;
	
	@NotBlank(message="Address must not be blank")
    private String address;
	
	
    private LocalDateTime createdAt; 
	
	
    private String createdBy;   
	
    private LocalDateTime updatedAt; 
	
	
    private String updatedBy;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(address, other.address) 
				&& Objects.equals(email, other.email) 
				&& gender == other.gender
				&& Objects.equals(memberID, other.memberID) 
				&& Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}


	@Override
	public int hashCode() {
		return Objects.hash(address, email, gender, memberID, name, phoneNumber);
	}  
    
    
}
