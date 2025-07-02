package com.spring.gym.beans;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int id;

    @NotBlank(message = "name Cannot be Blank")
    @NotEmpty(message = "name cannot be Empty")
    @NotNull(message="name cannot be null")
    private String name;

    @Positive(message="Age Must be Positive")
    @Max(value = 120, message = "age should be less than 120")
    @NotNull(message="age cannot be null")
    private int age;

    @NotNull(message = "memberships list cannot be null")
    @Size(min = 1, message = "memberships must contain at least one membership")
    private List<@NotBlank(message = "membership entries cannot be blank") String> memberships;

    @NotNull(message = "joinDate cannot be null")
    @PastOrPresent(message = "joinDate must be in the past or present")
    private Date joinDate;

    private Date expireDate;

    @NotNull(message = "status cannot be null")
    @Pattern(regexp = "ACTIVE|INACTIVE", message = "status must be ACTIVE, INACTIVE")
    private String status;
    
}
