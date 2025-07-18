package com.orders.service;

import java.util.List;

import com.orders.carts.domain.CartItem;
import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;
import com.orders.exceptions.InvalidOrderException;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;

public interface OrderService {
	Order placeNewOrder(Order order) throws OrderDatabaseOperationException, InvalidOrderException;

	List<Order> getMyOrders(SearchOrderCriteria criteria)
			throws OrderNotFoundException, OrderDatabaseOperationException;

	void updateMyOrder(SearchOrderCriteria criteria) throws OrderDatabaseOperationException, InvalidOrderException;

	Order getOrderDetails(Long orderId, Long customerId)
			throws OrderDatabaseOperationException, InvalidOrderException, OrderNotFoundException;

	Order createOrderFromCartItems(Long userId, List<CartItem> cartItems, String address);
}
