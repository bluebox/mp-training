package com.spring.ims.domain;

import lombok.Data;

@Data
public class Product {
	
	private int productId;
	private int supplierId;
	private String productName;
	private float productCost;
	

}
