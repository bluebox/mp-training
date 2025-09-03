package com.sms.models;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetails {

    private Long batchId;

//    @NotNull(message = "Purchase ID is required")
//    @Positive(message = "Purchase ID must be a positive number")
    private Long purchaseId;

    @NotBlank(message = "Product ID is required")
    @Size(min = 1, max = 50, message = "Product ID must not be empty and should be between 1 and 50 characters")
    private String productId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Taxable amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Taxable amount must be 0 or greater")
    private double taxableAmount;

    @NotNull(message = "GST amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "GST amount must be 0 or greater")
    private double gstAmount;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Total amount must be greater than 0")
    private double totalAmount;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiry;
}
