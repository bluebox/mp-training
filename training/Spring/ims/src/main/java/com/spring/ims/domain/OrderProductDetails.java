package com.spring.ims.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDetails {
	
	private int orderDetailsId;
	private int orderId;
	private String supplier;
	private String product;
	private int productQuantity;
	private float productCost;

	
	

}
