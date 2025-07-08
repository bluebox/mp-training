package com.spring.ims.domain;

import lombok.Data;

@Data
public class EmployeeProducts {
	
	private int stockId;
	private String productName;
	private int productId;
	private String supplier;
	private int quantity;
	private int minQuantity;
	private int maxQuantity;

}
