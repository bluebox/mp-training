package com.sms.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockOutHeader {
    private Long stockoutId;
    private Long supplierId;
   
    private double totalTaxableAmount;
    private double totalGst;
    private double totalAmount;
    
    private LocalDateTime approvedAt;
    private List<StockOutDetails> stockOutDetails;
    private String approvedBy;
}

