package com.storefront.controller;

import com.storefront.service.ReadingOrdersService;

public class TakingOrdersController {
	public static void main(String[] args) {
		new ReadingOrdersService().readOrders("src/com/storefront/orders.csv");
	}
}