package com.lms.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Positive(message = "ID must be a positive number.")
    private int id;

    @Positive(message = "Employee ID must be a positive number.")
    private int employeeId;

    @NotNull(message = "Date cannot be null.")
    @PastOrPresent(message = "Date must be in the past or present.")
    private Date date;

    private Time checkIn;

    private Time checkOut;

    @DecimalMin(value = "0.00", inclusive = true, message = "Working hours must be zero or a positive value.")
    @DecimalMax(value = "24.00", inclusive = true, message = "Working hours cannot exceed 24.")
    private BigDecimal workingHrs;

    @NotBlank(message = "Attendance status cannot be blank.")
    private String attendenceStatus;

}