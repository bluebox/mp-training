package com.orders.item.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orders.exceptions.InvalidOrderException;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.item.domain.OrderItem;
import com.orders.item.service.OrderItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

	private final OrderItemService orderItemService;

	@GetMapping("/{orderId}")
	public List<OrderItem> getItemsByOrder(@PathVariable Long orderId)
			throws InvalidOrderException, OrderNotFoundException, OrderDatabaseOperationException {
		return orderItemService.getItemsForOrderId(orderId);
	}
}