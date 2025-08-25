package com.VIMS.VIMSBackend.Model;

import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @NotNull(message="UserId must not be Null")
    private int userId;

    @NotBlank(message = "First Name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    private String userFirstname;

    @NotBlank(message = "Last Name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can only contain letters and spaces")
    private String userLastname;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    private String userEmail;

    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$",
             message = "Password must contain at least one digit, lowercase, uppercase, and special character.")
    private String userPassword;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String userPhone;

    private String userAddress;

    @NotNull(message = "Age cannot be empty")
    @Min(value = 18, message = "Age must be 18 or older")
    @Max(value = 100, message = "Age cannot exceed 100")
    private int userAge;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userRegistationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userModifiedDate;

    private Role role;
    
}
