package com.orders.service;

import java.util.List;

import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;

public interface OrderService {
	Order placeNewOrder(Order order);

	List<Order> getMyOrders(SearchOrderCriteria criteria);

	Order getOrderDetails(Long orderId, Long customerId);

	void updateOrderStatus(Long orderId, Long customerId);;
}
