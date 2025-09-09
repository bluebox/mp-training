package com.medplus.exptracker.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseForEmployeeDTO {
    private Integer id;
    private String categoryName;
    private String categoryId;
    private LocalDate date;
    private BigDecimal amount;
    private String description;
    private String remarks;
    private String status;
    private String receiptUrl;
}
