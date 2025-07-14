package com.spring.InventryManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.InventryManagementSystem.domain.OrderDetails;
import com.spring.InventryManagementSystem.domain.Product;
import com.spring.InventryManagementSystem.interfaces.repository.ItemRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.repository.OrderDetailsRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.repository.ProductRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.repository.SupplierRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.service.InfoServiceInterface;

@Service
public class InfoService implements InfoServiceInterface {
	
	private ItemRepositoryInterface itemRepository;
	private OrderDetailsRepositoryInterface orderDetailsRepository;
	private ProductRepositoryInterface productRepository;
	private SupplierRepositoryInterface supplierRepository;
	
	@Autowired
	public InfoService(ItemRepositoryInterface itemRepository, OrderDetailsRepositoryInterface orderDetailsRepository,
			ProductRepositoryInterface productRepository, SupplierRepositoryInterface supplierRepository) {
		this.itemRepository = itemRepository;
		this.orderDetailsRepository = orderDetailsRepository;
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
	}

	@Override
	public List<OrderDetails> pendingOrders() throws Exception {
		return orderDetailsRepository.pendingOrders();
	}

	@Override
	public List<OrderDetails> allOrdersAdmin() throws Exception {
		return orderDetailsRepository.allOrdersAdmin();
	}

	@Override
	public List<OrderDetails> allOrders() throws Exception {
		return orderDetailsRepository.allOrders();
	}

	@Override
	public List<Product> lowStock() {
		return productRepository.lowStock();
	}

}
