package com.consumerExample.orders.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Orders {

	private int orderId;
	private int customerId;
	private int waiterId;
	private LocalDate orderDate;
	private double amount;
	private OrderStatus status;

}
