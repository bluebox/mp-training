package com.vardhan.main.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Integer memberId;
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only alphabetic characters and spaces")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;
    
    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile should be 10-15 digits")
    @Size(max = 15, message = "Mobile must not exceed 15 characters")
    private String mobile;
    
    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other|M|F|O)$", message = "Gender must be Male, Female, or Other")
    @Size(max = 10, message = "Gender must not exceed 10 characters")
    private String gender;
    
    @NotBlank(message = "Address is required")
    @Size(max = 150, message = "Address must not exceed 150 characters")
    private String address;
    
    @Builder.Default
    @Pattern(regexp = "^(ACTIVE|INACTIVE|DELETED)$", message = "Status must be ACTIVE, INACTIVE, or DELETED")
    private String status = "ACTIVE";
    
    public Member(String name, String email, String mobile, String gender, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
        this.status = "ACTIVE"; 
    }
    
    public boolean isActive() {
        return "ACTIVE".equals(this.status);
    }
    
    public boolean isInactive() {
        return "INACTIVE".equals(this.status);
    }
    
    public boolean isDeleted() {
        return "DELETED".equals(this.status);
    }
    
    public void markAsDeleted() {
        this.status = "DELETED";
    }
    
    public void markAsActive() {
        this.status = "ACTIVE";
    }
    
    public void markAsInactive() {
        this.status = "INACTIVE";
    }
}
