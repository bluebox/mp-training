package com.spring.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ims.domain.EmployeeProducts;
import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.OrderProductDetails;
import com.spring.ims.domain.Orders;
import com.spring.ims.interfaces.service.AdminServiceInterface;
import com.spring.ims.repository.EmployeeProductsRepository;
import com.spring.ims.repository.OrderProductDetailsRepository;
import com.spring.ims.repository.OrdersRepository;
import com.spring.ims.repository.ProductRepository;
import com.spring.ims.repository.SupplierRepository;

@Service
public class AdminService implements AdminServiceInterface{
	
	private EmployeeProductsRepository employeeProductsRepository;
	private OrderProductDetailsRepository orderProductDetailsRepository;
	private OrdersRepository ordersRepository;
	private ProductRepository productRepository;
	private SupplierRepository supplierRepository;

	@Autowired
	public AdminService(EmployeeProductsRepository employeeProductsRepository,
			OrderProductDetailsRepository orderProductDetailsRepository, OrdersRepository ordersRepository,
			ProductRepository productRepository, SupplierRepository supplierRepository) {
		this.employeeProductsRepository = employeeProductsRepository;
		this.orderProductDetailsRepository = orderProductDetailsRepository;
		this.ordersRepository = ordersRepository;
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
	}

	// Admin Approve And Reject
	@Transactional
	public boolean updateStatus(Orders order) {
		try {
			if (ordersRepository.checkStatus(order)) {
				ordersRepository.approval(order);
			} else {
				return false;
			}
			List<OrderProductDetails> products = orderProductDetailsRepository.ProductsOfOrder(order);
			for (int i = 0; i < products.size(); i++) {
				String[] forId = products.get(i).getProduct().split(" - ");
				if (employeeProductsRepository.isPresent(Integer.parseInt(forId[1]))) {
					int quantity = employeeProductsRepository.retrivingByID(Integer.parseInt(forId[1]));
					quantity = quantity + products.get(i).getProductQuantity();
					employeeProductsRepository.updateQuantity(Integer.parseInt(forId[1]), quantity);
				} else {
					EmployeeProducts product = new EmployeeProducts();
					product.setProductId(Integer.parseInt(forId[1]));
					product.setProductName(forId[0]);
					product.setQuantity(products.get(i).getProductQuantity());
					product.setSupplier(products.get(i).getSupplier());
					employeeProductsRepository.insertProduct(product);

				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("Error in updateStatus in Implementation : " + e.getMessage());
			return false;
		}
	}

	// Pending Requests
		public List<Orders> pendingOrders() {
			return ordersRepository.pendingOrders();
		}

		// All Orders
		public List<Orders> allOrders() {
			return ordersRepository.adminAllOrders();
		}

		// Products Of Order
		public FullOrder productsOfOrder(Orders order) {
			FullOrder fullOrder = new FullOrder();
			fullOrder.setOrders(order);
			fullOrder.setOrderProductDetails(orderProductDetailsRepository.ProductsOfOrder(order));
			return fullOrder;
		}

}
