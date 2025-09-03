package com.sms.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockOutDetails {
    private Long batchId;
    private Long stockoutId;
    private String productId;
    private Integer quantity;
    private char type;
    private double taxableAmount;
    private double gstAmount;
    private double totalAmount;
    private LocalDate expiry;
}
