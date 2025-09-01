package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.OrderDetails;

public interface OrderDetailsService {

//	List<OrderDetails> getAllOrderDetails();
	boolean insertOrderDetails(List<OrderDetails> orderDetailsList);
	
	List<OrderDetails> selectAllOrderDetails();
	
	
}
