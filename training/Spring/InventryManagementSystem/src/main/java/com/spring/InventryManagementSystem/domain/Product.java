package com.spring.InventryManagementSystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	Integer productId;
	String supplier;
	String productName;
	float productCost;
	Integer quantity;
	Integer minQuantity;
	Integer maxQuantity;

}
