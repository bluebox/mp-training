package com.users.Users.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainUser  extends User{
	
    private String userCode;
    
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$",
    message = "Password should be 8-20 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;

}

