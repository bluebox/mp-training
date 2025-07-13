package com.orders.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;
import com.orders.exceptions.InvalidOrderException;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order-service")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/create")
	public Order placeOrder(@RequestBody Order order) throws OrderDatabaseOperationException, InvalidOrderException {
		return orderService.placeNewOrder(order);
	}

	@PostMapping("/search")
	public List<Order> getOrdersByCriteria(@RequestBody SearchOrderCriteria criteria)
			throws InvalidOrderException, OrderNotFoundException, OrderDatabaseOperationException {
		if (criteria.getPageNumber() != null && criteria.getPageNumber() < 0) {
			throw new InvalidOrderException("Page number cannot be negative");
		}
		if (criteria.getPageSize() != null && criteria.getPageSize() <= 0) {
			throw new InvalidOrderException("Page size must be greater than 0");
		}
		return orderService.getMyOrders(criteria);
	}

	@GetMapping("/order-details")
	public Order getMyOrderDetails(Long orderId, Long customerId)
			throws InvalidOrderException, OrderDatabaseOperationException, OrderNotFoundException {
		if (orderId == null || orderId < 0) {
			throw new InvalidOrderException("Order ID cannot be empty");
		}
		if (customerId == null || customerId < 0) {
			throw new InvalidOrderException("Customer ID cannot be empty or negative.");
		}
		SearchOrderCriteria criteria = new SearchOrderCriteria();
		criteria.setCustomerId(customerId);
		List<Long> allOrderIds = orderService.getMyOrders(criteria).stream().map(Order::getOrderId)
				.collect(Collectors.toList());
		if (!allOrderIds.contains(orderId)) {
			throw new InvalidOrderException("Given order ID does not exist for this customer.");
		}
		return orderService.getOrderDetails(orderId, customerId);
	}

	@PostMapping("/update-order")
	public void updateMyOrder(@RequestBody SearchOrderCriteria criteria)
			throws InvalidOrderException, OrderDatabaseOperationException {
		if (criteria.getCustomerId() == null || criteria.getCustomerId() <= 0) {
			throw new InvalidOrderException("Customer ID cannot be null or negative.");
		}
		if (criteria.getOrderIds() == null || criteria.getOrderIds().isEmpty()) {
			throw new InvalidOrderException("At least one Order ID must be provided.");
		}
		orderService.updateMyOrder(criteria);
	}
}
