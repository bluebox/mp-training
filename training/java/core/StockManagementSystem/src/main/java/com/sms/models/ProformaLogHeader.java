package com.sms.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProformaLogHeader {
 private Long logId;
 private Long proformaId;
 private Long supplierId;
 private double totalTaxableAmount;
 private double totalGst;
 private double totalAmount;
 private char editType; // E / D
 private LocalDateTime changedAt;
 private String changedBy;
}

