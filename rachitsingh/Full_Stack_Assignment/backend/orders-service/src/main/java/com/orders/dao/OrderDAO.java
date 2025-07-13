package com.orders.dao;

import java.util.List;

import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;

public interface OrderDAO {
	Order createOrder(Order order) throws OrderDatabaseOperationException;

	List<Order> fetchCustomerOrders(SearchOrderCriteria criteria) throws OrderDatabaseOperationException;

	void updateMyOrder(SearchOrderCriteria criteria) throws OrderDatabaseOperationException;

	Order fetchCustomerOrderWithItems(Long orderId, Long customerId)
			throws OrderNotFoundException, OrderDatabaseOperationException;
}
