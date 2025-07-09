package com.spring.ims.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	
	private int orderId;
	private Date orderDate;
	private float orderCost;
	private int orderDiscount;
	private String orderStatus;
	

}
