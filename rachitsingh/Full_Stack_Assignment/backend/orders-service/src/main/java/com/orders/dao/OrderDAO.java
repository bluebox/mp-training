package com.orders.dao;

import java.util.List;

import com.orders.Exceptions.OrderDatabaseOperationException;
import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;

public interface OrderDAO {
	Order createOrder(Order order) throws OrderDatabaseOperationException;

	List<Order> fetchCustomerOrders(SearchOrderCriteria criteria) throws OrderDatabaseOperationException;

//	void updateMyOrder(Long orderId, Long customerId);

	Order fetchCustomerOrderWithItems(Long orderId, Long customerId);
}
