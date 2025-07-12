package com.orders.service;

import java.util.List;

import com.orders.Exceptions.InvalidOrderException;
import com.orders.Exceptions.OrderDatabaseOperationException;
import com.orders.Exceptions.OrderNotFoundException;
import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;

public interface OrderService {
	Order placeNewOrder(Order order) throws OrderDatabaseOperationException, InvalidOrderException;

	List<Order> getMyOrders(SearchOrderCriteria criteria)
			throws OrderNotFoundException, OrderDatabaseOperationException;

	void updateMyOrder(SearchOrderCriteria criteria) throws OrderDatabaseOperationException, InvalidOrderException;

	Order getOrderDetails(Long orderId, Long customerId) throws OrderDatabaseOperationException, InvalidOrderException;
}
