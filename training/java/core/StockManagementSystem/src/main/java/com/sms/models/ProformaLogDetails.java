package com.sms.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaLogDetails {
    private Long logId;
    private Long batchId;
    private String productId;
    private Integer quantity;
    private double taxableAmount;
    private double gstAmount;
    private double totalAmount;
    private LocalDate expiry;
    private char editType; // E / D
}

