package com.medplus.exptracker.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    private Integer id;

    @NotNull(message = "Employee ID is required")
    private Integer employeeId;

    @NotNull(message = "Category is required")
    private Integer categoryId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.0", message = "Amount must be greater than 0")
    @DecimalMax(value = "50000.0", message = "Amount cannot be greater than 50,000")
    private BigDecimal amount;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @PastOrPresent(message="Date must be today or past dates")
    private LocalDate date;
    
    private String status;
    
    @NotNull(message="ManagerID is required!")
    private Integer managerId;

    private String remarks;

    private String receiptUrl;
}