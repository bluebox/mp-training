package com.spring.ims.domain;

import lombok.Data;

@Data
public class OrderProductDetails {
	
	private int orderDetailsId;
	private int orderId;
	private String supplier;
	private String product;
	private int productQuantity;
	private float productCost;

}
