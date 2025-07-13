package com.spring.InventryManagementSystem.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
	
	Integer orderId;
	Date orderDate;
	Float orderCost;
	Integer orderDiscount;
	String orderStatus;
	String orderSupplier;
	List<Item> item;

}
