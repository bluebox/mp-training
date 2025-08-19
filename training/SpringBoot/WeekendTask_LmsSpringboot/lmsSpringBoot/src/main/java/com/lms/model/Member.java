package com.lms.model;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	

    private int memberId;
    

    @NotBlank(message="Invalid name: Name can't be blank")
    @Pattern(regexp="^[A-Za-z ]+$", message="Invalid Name: Name should only consist of letters and spaces")
    @Size(min=3, max=50, message="Invalid name: Must be 3-50 characters long")
    private String name;

    @NotBlank(message="Email can't be blank")
    @Email(message="Invalid email format")
    private String email;

    @NotBlank(message="Mobile number can't be blank")
    @Pattern(regexp="^[0-9]{10}$", message="Mobile Number must contain 10 digits")
    private String mobile;

    @NotBlank(message="Please select the gender from dropdown")
    @Pattern(regexp="Male|Female|Other", message="Gender must be Male, Female, or Other")
    private String gender;

    @NotBlank(message="Address can't be blank")
    @Size(min=10, max=80, message="Address must be between 10 and 80 characters")
    private String address;

 
    public Member(String name, String email, String mobile, String gender, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

}
