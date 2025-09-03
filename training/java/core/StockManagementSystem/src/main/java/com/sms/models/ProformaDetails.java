package com.sms.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProformaDetails {
	     private Long batchId;
	    private Long proformaId;
	    private String productId;
	    private Integer quantity;
	    private double taxableAmount;
	    private double gstAmount;
	    private double totalAmount;
	    private LocalDate expiry;
	    private char status; // C / E / D / A


}
