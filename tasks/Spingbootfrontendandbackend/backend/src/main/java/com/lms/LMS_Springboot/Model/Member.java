package com.lms.LMS_Springboot.Model;

import com.lms.LMS_Springboot.Model.checking_enum.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Member {

    private int memberid;

    @NotBlank(message = "name is required")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "mobile number is required")
    @Pattern(regexp = "^[0-9]\\d{9}$", message = "Mobile number must be 10 digits ")
    private String mobile;

    @NotBlank(message = "address is required")
    private String address;

    @NotNull(message = "gender is required")
    private Gender gender;

    public Member(String name, String email, String mobile, String address, Gender gender) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.gender = gender;
    }
}
