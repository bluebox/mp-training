package com.spring.InventryManagementSystem.interfaces.repository;

import java.sql.SQLException;
import java.util.List;

import com.spring.InventryManagementSystem.domain.OrderDetails;

public interface OrderDetailsRepositoryInterface {

	int insertOrderDetails(OrderDetails orderDetails);

	List<OrderDetails> pendingOrders() throws SQLException;

	List<OrderDetails> allOrdersAdmin() throws SQLException;

	List<OrderDetails> allOrders() throws SQLException;

	boolean withdrawOrder(Integer orderId) throws SQLException;

}
