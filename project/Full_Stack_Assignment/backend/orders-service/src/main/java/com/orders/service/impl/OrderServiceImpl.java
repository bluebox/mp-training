package com.orders.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.orders.carts.dao.CartDAO;
import com.orders.carts.domain.CartItem;
import com.orders.dao.OrderDAO;
import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;
import com.orders.enums.OrderStatus;
import com.orders.exceptions.InvalidOrderException;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.item.domain.OrderItem;
import com.orders.item.service.OrderItemService;
import com.orders.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderDAO orderDAO;
	private final OrderItemService orderItemService;
	private final CartDAO cartDAO;
	
	@Override
	public Order createOrderFromCartItems(Long userId,List<CartItem> cartItems,String address) {
	    List<OrderItem> orderItems = cartItems.stream()
	            .map(cartItem -> {
	                OrderItem orderItem = new OrderItem();
	                orderItem.setProductId(cartItem.getProductId());
	                orderItem.setQuantity(cartItem.getQuantity());
	                orderItem.setPrice(cartItem.getPrice());
	                orderItem.setCreatedAt(LocalDateTime.now());
	                return orderItem;
	            })
	            .collect(Collectors.toList());
	    BigDecimal totalAmount = orderItems.stream()
	            .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
	            .reduce(BigDecimal.ZERO, BigDecimal::add);

	    Order newOrder = new Order();
	    newOrder.setUserId(userId);
	    newOrder.setAddress(address);
	    newOrder.setTotalAmount(totalAmount);
	    newOrder.setStatus(OrderStatus.PROCESSING);
	    newOrder.setPlacedAtDate(LocalDateTime.now());

	    try {
	        Order savedOrder = orderDAO.createOrder(newOrder);

	        for (OrderItem item : orderItems) {
	            item.setOrderId(savedOrder.getOrderId());
	        }
	        orderItemService.saveOrderItems(orderItems);

	        cartDAO.clearCart(userId);

	        savedOrder.setOrderItems(orderItems);
	        return savedOrder;

	    } catch (Exception e) {
	        log.error("Error while creating order from cart", e);
	        throw new RuntimeException("Could not create order", e);
	    }
	}

	@Override
	public Order placeNewOrder(Order order) throws OrderDatabaseOperationException, InvalidOrderException {
		validateOrderFields(order);
		try {
			Order savedOrder = orderDAO.createOrder(order);
			order.getOrderItems().forEach(item -> item.setOrderId(savedOrder.getOrderId()));
			orderItemService.saveOrderItems(order.getOrderItems());
			return savedOrder;
		} catch (DataAccessException e) {
			log.error("Error while creating the order.", e);
			throw new OrderDatabaseOperationException("Error while creating the order record", e);
		}
	}

	@Override
	public List<Order> getMyOrders(SearchOrderCriteria criteria)
			throws OrderNotFoundException, OrderDatabaseOperationException {
		try {
			List<Order> orders = orderDAO.fetchCustomerOrders(criteria);
			return orders != null ? orders : Collections.emptyList();
		} catch (DataAccessException e) {
			log.error("Error while fetching customer orders.", e);
			throw new OrderDatabaseOperationException("Error while fetching customer orders.", e);
		}
	}

	@Override
	public void updateMyOrder(SearchOrderCriteria criteria)
			throws OrderDatabaseOperationException, InvalidOrderException {
		if (criteria.getCustomerId() == null || criteria.getCustomerId() <= 0) {
			throw new InvalidOrderException("Customer ID is not provided or it is not a valid number.");
		}
		if (criteria.getOrderIds() == null || criteria.getOrderIds().isEmpty()) {
			throw new InvalidOrderException("No Order ID provided for update.");
		}
		try {
			orderDAO.updateMyOrder(criteria);
		} catch (DataAccessException e) {
			log.error("Error while cancelling or updating order", e);
			throw new OrderDatabaseOperationException(
					"Error while cancelling the orders or updating the delivery address.", e);
		}
	}

	@Override
	public Order getOrderDetails(Long orderId, Long customerId)
			throws OrderDatabaseOperationException, InvalidOrderException, OrderNotFoundException {
		if (orderId == null || orderId <= 0) {
			throw new InvalidOrderException("No order Id provided to fetch details.");
		}
		if (customerId == null || customerId <= 0) {
			throw new InvalidOrderException("No customer Id provided to fetch particular order details.");
		}
		try {
			return orderDAO.fetchCustomerOrderWithItems(orderId, customerId);
		} catch (DataAccessException e) {
			log.error("Failed to fetch order details for orderId={} customerId={}", orderId, customerId, e);

			throw new OrderDatabaseOperationException("Error occured while fetching specific order's details.", e);
		}

	}

	private void validateOrderFields(Order order) throws InvalidOrderException {
		if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
			throw new InvalidOrderException("There must be atleast one item as a part of Order.");
		}
		if (order.getAddress() == null || order.getAddress().trim().isEmpty()) {
			throw new InvalidOrderException("Delievery address must be specified.");
		}
		if (order.getPlacedAtDate() == null) {
			throw new InvalidOrderException("Date of order placement is not provided.");
		}
		if (order.getTotalAmount().signum() <= 0) {
			throw new InvalidOrderException("Net amount of order must be a positive value.");
		}
		if (order.getUserId() == null || order.getUserId() <= 0) {
			throw new InvalidOrderException("Customer ID for the given order cannot be empty or null");
		}
	}
}
