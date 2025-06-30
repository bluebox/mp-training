package com.example.practice.beans;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @NotBlank
    @Size(min = 3, max = 50, message = "The name should be between 3 and 50 chars")
    private String name;

    @Pattern(regexp = "\\d{10}", message = "Enter exactly 10 digits")
    private String number;

    @Min(value = 10, message = "Age must be at least 10")
    @Max(value = 99, message = "Age must be at most 99")
    private int age;

    @Email
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank
    @Size(min = 3, max = 50, message = "The address should be between 3 and 50 chars")
    private String address;
}
