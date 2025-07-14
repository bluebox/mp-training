package com.spring.InventryManagementSystem.interfaces.repository;

import java.util.List;

import com.spring.InventryManagementSystem.domain.Product;

public interface ProductRepositoryInterface {

	List<Product> getAllProducts(String supplier);

	List<Product> lowStock();

}
