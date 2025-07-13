package com.orders.item.dao;

import java.util.List;

import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.item.domain.OrderItem;

public interface OrderItemDAO {
	void storeOrderItems(List<OrderItem> items) throws OrderDatabaseOperationException;

	List<OrderItem> fetchOrderItemsByOrderId(Long orderId)
			throws OrderNotFoundException, OrderDatabaseOperationException;
}
