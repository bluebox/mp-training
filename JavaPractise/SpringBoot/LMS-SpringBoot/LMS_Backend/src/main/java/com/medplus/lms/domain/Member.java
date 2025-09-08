package com.medplus.lms.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Member {
    private int memberId;

    @NotBlank(message = "Name is required")
    @Size(max = 35, message = "Name cannot exceed 35 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Mobile is required")
    @Size(min = 10, max = 10, message = "Mobile must be 10 digits")
    private String mobile;

    private Gender gender;

    @NotBlank(message = "Address is required")
    private String address;

    private Status status;

    private String createdBy;
    private LocalDateTime createdAt;

    private String updatedBy;
    private LocalDateTime updatedAt;
    public boolean equalsForUpdate(Member other) {
        if (other == null) return false;
        return Objects.equals(name, other.name)
            && Objects.equals(email, other.email)
            && Objects.equals(mobile, other.mobile)
            && Objects.equals(gender, other.gender)
            && Objects.equals(address, other.address)
            && Objects.equals(status, other.status);
    }
}
