package com.sms.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseHeader {

    private Long purchaseId;

    @NotNull(message = "Supplier ID is required")
    @Positive(message = "Supplier ID must be a positive number")
    private Long supplierId;

    @NotNull(message = "Total taxable amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total taxable amount must be 0 or greater")
    private double totalTaxableAmount;

    @NotNull(message = "Total GST is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total GST must be 0 or greater")
    private double totalGst;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Total amount must be greater than 0")
    private double totalAmount;

    private LocalDateTime createdAt;

    @NotBlank(message = "Created by is required")
    private String createdBy;

    @NotNull(message = "Purchase details cannot be null")
    
    private List<PurchaseDetails> pd;
}
