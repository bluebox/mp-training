package com.spring.InventryManagementSystem.interfaces.service;

import java.util.List;

import com.spring.InventryManagementSystem.domain.OrderDetails;
import com.spring.InventryManagementSystem.domain.Product;

public interface OrderServiceInterface {

	List<String> getAllSuppliers()  throws Exception;

	List<Product> getAllProducts(String supplier) throws Exception;

	boolean purchaseCreation(OrderDetails orderDetails);

	boolean withdrawOrder(Integer orderId) throws Exception;

}
