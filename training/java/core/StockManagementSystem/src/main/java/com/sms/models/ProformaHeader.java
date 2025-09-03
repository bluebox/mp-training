package com.sms.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProformaHeader {
	    private Long proformaId;
	    private Long supplierId;
	    private double totalTaxableAmount;
	    private double totalGst;
	    private double totalAmount;
	    private char status; // C / E / D / A
	    private LocalDate createdAt;
	    private String createdBy;
	     public List<ProformaDetails>pd;


}
