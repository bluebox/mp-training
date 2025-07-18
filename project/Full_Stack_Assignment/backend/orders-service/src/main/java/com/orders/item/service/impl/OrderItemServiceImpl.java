package com.orders.item.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.orders.exceptions.InvalidOrderException;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.item.dao.OrderItemDAO;
import com.orders.item.domain.OrderItem;
import com.orders.item.service.OrderItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
	private final OrderItemDAO orderItemDAO;

	@Override
	public void saveOrderItems(List<OrderItem> items) throws InvalidOrderException, OrderDatabaseOperationException {
		validateOrderItems(items);
		try {
			orderItemDAO.storeOrderItems(items);
		} catch (DataAccessException e) {
			log.error("Error storing order items", e);
			throw new OrderDatabaseOperationException("Error while saving order items to the database.", e);
		}
	}

	@Override
	public List<OrderItem> getItemsForOrderId(Long orderId)
			throws InvalidOrderException, OrderNotFoundException, OrderDatabaseOperationException {
		if (orderId == null || orderId <= 0) {
			throw new InvalidOrderException("Invalid or missing Order ID.");
		}
		try {
			List<OrderItem> items = orderItemDAO.fetchOrderItemsByOrderId(orderId);
			if (items == null || items.isEmpty()) {
				throw new OrderNotFoundException("No items found for the given order ID.");
			}
			return items;
		} catch (DataAccessException e) {
			log.error("Error fetching order items for orderId: {}", orderId, e);
			throw new OrderDatabaseOperationException("Failed to fetch order items from the database.", e);
		}
	}

	private void validateOrderItems(List<OrderItem> items) throws InvalidOrderException {
		if (items == null || items.isEmpty()) {
			throw new InvalidOrderException("Order must contain at least one item.");
		}

		for (OrderItem item : items) {
			if (item.getOrderId() == null || item.getOrderId() <= 0) {
				throw new InvalidOrderException("Each order item must have a valid Order ID.");
			}
			if (item.getProductId() == null || item.getProductId() <= 0) {
				throw new InvalidOrderException("Each order item must have a valid Product ID.");
			}
//			if (item.getProductName() == null || item.getProductName().trim().isEmpty()) {
//				throw new InvalidOrderException("Each order item must have a valid Product Name.");
//			}
			if (item.getQuantity() <= 0) {
				throw new InvalidOrderException("Quantity must be greater than zero.");
			}
			if (item.getPrice() == null || item.getPrice().signum() <= 0) {
				throw new InvalidOrderException("Price must be a positive value.");
			}
		}
	}
}
