package com.spring.InventryManagementSystem.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.InventryManagementSystem.domain.Item;
import com.spring.InventryManagementSystem.domain.OrderDetails;
import com.spring.InventryManagementSystem.domain.Product;
import com.spring.InventryManagementSystem.domain.Supplier;
import com.spring.InventryManagementSystem.interfaces.repository.ItemRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.repository.OrderDetailsRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.repository.ProductRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.repository.SupplierRepositoryInterface;
import com.spring.InventryManagementSystem.interfaces.service.OrderServiceInterface;

@Service
public class OrderService implements OrderServiceInterface {

	private ItemRepositoryInterface itemRepository;
	private OrderDetailsRepositoryInterface orderDetailsRepository;
	private ProductRepositoryInterface productRepository;
	private SupplierRepositoryInterface supplierRepository;

	@Autowired
	public OrderService(ItemRepositoryInterface itemRepository, OrderDetailsRepositoryInterface orderDetailsRepository,
			ProductRepositoryInterface productRepository, SupplierRepositoryInterface supplierRepository) {
		this.itemRepository = itemRepository;
		this.orderDetailsRepository = orderDetailsRepository;
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;

	}

	@Override
	public List<String> getAllSuppliers() throws SQLException {
		List<Supplier> suppliers = supplierRepository.getAllSuppliers();
		List<String> supplierNames = suppliers.stream()
				.map(n -> n.getSupplierName()+" - "+n.getSupplierId())
				.collect(Collectors.toList());
		return supplierNames;
	}

	@Override
	public List<Product> getAllProducts(String supplier) throws SQLException {
		return productRepository.getAllProducts(supplier);
	}

	@Override
	@Transactional
	public boolean purchaseCreation(OrderDetails orderDetails) {
		if (orderDetails.getOrderId() == null) {
			orderDetails.setOrderStatus("PENDING");
			orderDetails.setOrderDate(new Date());
			int orderId = orderDetailsRepository.insertOrderDetails(orderDetails);
			orderDetails.getItem().stream()
		    .map(n -> { n.setOrderId(orderId); return n; })
		    .forEach(n -> itemRepository.insertItems(n));
			return true;
		}
		return false;
	}

	@Override
	public boolean withdrawOrder(Integer orderId) throws Exception {
		return orderDetailsRepository.withdrawOrder(orderId);
	}
}
