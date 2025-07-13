package com.spring.InventryManagementSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
	
	Integer supplierId;
	String supplierName;
	Long supplierContact;
	String supplierAddress;

}
