package com.spring.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ims.domain.EmployeeProducts;
import com.spring.ims.domain.Product;
import com.spring.ims.interfaces.service.LowStockServiceInterface;
import com.spring.ims.repository.EmployeeProductsRepository;
import com.spring.ims.repository.OrderProductDetailsRepository;
import com.spring.ims.repository.OrdersRepository;
import com.spring.ims.repository.ProductRepository;
import com.spring.ims.repository.SupplierRepository;

@Service
public class LowStockService implements LowStockServiceInterface {
	
	private EmployeeProductsRepository employeeProductsRepository;
	private OrderProductDetailsRepository orderProductDetailsRepository;
	private OrdersRepository ordersRepository;
	private ProductRepository productRepository;
	private SupplierRepository supplierRepository;

	@Autowired
	public LowStockService(EmployeeProductsRepository employeeProductsRepository,
			OrderProductDetailsRepository orderProductDetailsRepository, OrdersRepository ordersRepository,
			ProductRepository productRepository, SupplierRepository supplierRepository) {
		this.employeeProductsRepository = employeeProductsRepository;
		this.orderProductDetailsRepository = orderProductDetailsRepository;
		this.ordersRepository = ordersRepository;
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
	}

	// Low Stock
	public List<EmployeeProducts> lowStock() {
		return employeeProductsRepository.lowStock();
	}

	public Product fetchProduct(EmployeeProducts employeeProsucts) {
		return productRepository.fetchProduct(employeeProsucts);
	}

}
