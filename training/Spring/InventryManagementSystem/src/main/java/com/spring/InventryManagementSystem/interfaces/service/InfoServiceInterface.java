package com.spring.InventryManagementSystem.interfaces.service;

import java.util.List;

import com.spring.InventryManagementSystem.domain.OrderDetails;

public interface InfoServiceInterface {

	List<OrderDetails> pendingOrders() throws Exception;

	List<OrderDetails> allOrdersAdmin() throws Exception;

	List<OrderDetails> allOrders() throws Exception;

}
