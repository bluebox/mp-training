package com.orders.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orders.Exceptions.InvalidOrderException;
import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;
import com.orders.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order-service")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/create")
	public Order placeOrder(@RequestBody Order order) {
		return orderService.placeNewOrder(order);
	}

	@PostMapping("/search")
	public List<Order> getOrdersByCriteria(@RequestBody SearchOrderCriteria criteria) throws InvalidOrderException {
		if (criteria.getPageNumber() != null && criteria.getPageNumber() < 0) {
			throw new InvalidOrderException("Page number cannot be negative");
		}
		if (criteria.getPageSize() != null && criteria.getPageSize() <= 0) {
			throw new InvalidOrderException("Page size must be greater than 0");
		}

		return orderService.getMyOrders(criteria);
	}

	@GetMapping("/order-details")
	public Order getMyOrderDetails(Long orderId, Long customerId) throws InvalidOrderException {
		SearchOrderCriteria criteria = new SearchOrderCriteria();
		criteria.setCustomerId(customerId);
		List<Long> allOrderIds = orderService.getMyOrders(criteria).stream().map(Order::getOrderId)
				.collect(Collectors.toList());
		;
		if (orderId == null || orderId < 0) {
			throw new InvalidOrderException("Order ID cannot be empty");
		}
		if (!allOrderIds.contains(orderId)) {
			throw new InvalidOrderException("Given order ID does not exits in the database");
		}
		if (customerId == null || customerId < 0) {
			throw new InvalidOrderException("Customer ID cannot be empty or negative.");
		}
		return orderService.getOrderDetails(orderId, customerId);
	}

	@PostMapping("/cancel-order")
	public void cancelMyOrder(Long orderId, Long customerId) throws InvalidOrderException {
		SearchOrderCriteria criteria = new SearchOrderCriteria();
		criteria.setCustomerId(customerId);
		List<Long> allOrderIds = orderService.getMyOrders(criteria).stream().map(Order::getOrderId)
				.collect(Collectors.toList());
		;
		if (orderId == null || orderId < 0) {
			throw new InvalidOrderException("Order ID cannot be empty");
		}
		if (!allOrderIds.contains(orderId)) {
			throw new InvalidOrderException("Given order ID does not exits in the database");
		}
		if (customerId == null || customerId < 0) {
			throw new InvalidOrderException("Customer ID cannot be empty or negative.");
		}

		orderService.updateOrderStatus(orderId, customerId);
	}

}
