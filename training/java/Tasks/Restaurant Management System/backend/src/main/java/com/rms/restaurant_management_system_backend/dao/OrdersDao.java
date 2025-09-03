package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Orders;

public interface OrdersDao {

	int addOrder(Orders order);

	int updateStatus(Orders order, String status);

	int updateAmount(Orders order, double amount);

	Orders getOrderById(int id);

	List<Orders> getAllOrders();

	int getOrderId(Orders order);

	List<Orders> getOrdersByCategory(String category);

	void insertLog(Orders order);

}
