package com.lms.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.sql.Date; 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {

    @Positive(message = "Request ID must be a positive number.")
    private int requestId; 

    @NotNull(message = "Employee Id cannot be null.")
    @Positive(message = "Employee ID must be a positive number.")
    private int employeeId;

    @NotBlank(message = "Leave type cannot be blank.")
    @Pattern(regexp = "^(CASUAL_LEAVE|SICK_LEAVE|EARNED_LEAVE|OPTIONAL_LEAVE)$", message = "Invalid leave type. Must be CASUAL_LEAVE, SICK_LEAVE, EARNED_LEAVE, or OPTIONAL_LEAVE.")
    private String leaveType;

    @NotNull(message = "Start date cannot be null.")
    @FutureOrPresent(message = "Start date must be in the present or future.")
    private Date startDate;

    @NotNull(message = "End date cannot be null.")
    @FutureOrPresent(message = "End date must be in the present or future.")
    private Date endDate;

    @NotBlank(message = "Remarks cannot be blank.")
    @Size(max = 255, message = "Remarks cannot exceed 255 characters.")
    private String remarks;

    @NotBlank(message = "Leave status cannot be blank.")
    @Pattern(regexp = "^(ACCEPTED|REJECTED|CANCELLED|PENDING)$", message = "Invalid leave status. Must be ACCEPTED, REJECTED, CANCELLED, or PENDING.")
    private String leaveStatus;

    @Size(max = 255, message = "Rejection reason cannot exceed 255 characters.")
    private String rejectionReason; 
}