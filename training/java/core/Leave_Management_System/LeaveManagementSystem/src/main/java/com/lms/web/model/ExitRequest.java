package com.lms.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExitRequest {

    private int requestId;

    @NotNull(message = "Employee ID cannot be null.")
    @Positive(message = "Employee ID must be a positive number.")
    private Integer employeeId;

    @NotNull(message = "Exit date cannot be null.")
    @FutureOrPresent(message = "Exit date must be in the present or future.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date exitDate;

    @Pattern(
        regexp = "^(PENDING|APPROVED|REJECTED|CANCELLED)$",
        message = "Invalid request status. Must be PENDING, APPROVED, REJECTED, or CANCELLED."
    )
    private String requestStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date requestSubmittedAt;

    @NotNull(message = "Remarks cannot be null.")
    @Size(max = 255, message = "Remarks cannot exceed 255 characters.")
    @Pattern(
        regexp = "^[a-zA-Z0-9\\s,.!?]*$",
        message = "Remarks can only contain letters, numbers, spaces, and punctuation."
    )
    private String remarks;
    
    
    @Size(max = 255, message = "Remarks cannot exceed 255 characters.")
    @Pattern(
        regexp = "^[a-zA-Z0-9\\s,.!?]*$",
        message = "Remarks can only contain letters, numbers, spaces, and punctuation."
    )

    private String adminRemarks;
}