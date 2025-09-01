package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.domain.Orders;

public interface OrdersService {

	void updateAmount(int OrderId);

	Orders getOrderById(int id);

	List<Orders> getAllOrders();

	List<Orders> getOrdersByCategory(String category);

	void updateStatus(int id, String status);

	Orders addOrder(String name, String phone, int waiterId, double totalPrice, List<OrderDetails> orderDetailsList);

}
