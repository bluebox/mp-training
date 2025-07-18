package com.orders.item.service;

import java.util.List;

import com.orders.exceptions.InvalidOrderException;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.item.domain.OrderItem;

public interface OrderItemService {
	void saveOrderItems(List<OrderItem> items) throws InvalidOrderException, OrderDatabaseOperationException;

	List<OrderItem> getItemsForOrderId(Long orderId)
			throws InvalidOrderException, OrderNotFoundException, OrderDatabaseOperationException;
}
