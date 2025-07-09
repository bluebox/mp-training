package com.spring.ims.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.OrderProductDetails;
import com.spring.ims.domain.Orders;
import com.spring.ims.interfaces.service.OrdersServiceInterface;
import com.spring.ims.repository.EmployeeProductsRepository;
import com.spring.ims.repository.OrderProductDetailsRepository;
import com.spring.ims.repository.OrdersRepository;
import com.spring.ims.repository.ProductRepository;
import com.spring.ims.repository.SupplierRepository;

@Service
public class OrdersService implements OrdersServiceInterface{

	private EmployeeProductsRepository employeeProductsRepository;
	private OrderProductDetailsRepository orderProductDetailsRepository;
	private OrdersRepository ordersRepository;
	private ProductRepository productRepository;
	private SupplierRepository supplierRepository;

	@Autowired
	public OrdersService(EmployeeProductsRepository employeeProductsRepository,
			OrderProductDetailsRepository orderProductDetailsRepository, OrdersRepository ordersRepository,
			ProductRepository productRepository, SupplierRepository supplierRepository) {
		this.employeeProductsRepository = employeeProductsRepository;
		this.orderProductDetailsRepository = orderProductDetailsRepository;
		this.ordersRepository = ordersRepository;
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
	}

	// Pending Requests
	public List<Orders> pendingOrders() {
		return ordersRepository.pendingOrders();
	}

	// All Orders
	public List<Orders> allOrders() {
		return ordersRepository.allOrders();
	}

	// Products Of Order
	public FullOrder productsOfOrder(Orders order) {
		FullOrder fullOrder = new FullOrder();
		fullOrder.setOrders(order);
		fullOrder.setOrderProductDetails(orderProductDetailsRepository.ProductsOfOrder(order));
		return fullOrder;
	}
	
	// cost of product
		public float costOfProduct(OrderProductDetails orderProductDetails) {
			String product = orderProductDetails.getProduct();
			String[] productCost = product.split(" - ");
			return productRepository.costOfProduct(Integer.parseInt(productCost[1]));
		}

	// editing Of Order
	@Transactional
	public boolean editingOfOrder(FullOrder fullOrder) {
		Orders order = fullOrder.getOrders();
		if (ordersRepository.checkStatus(order)) {
			List<OrderProductDetails> listOfProducts = fullOrder.getOrderProductDetails();
			List<OrderProductDetails> excistingProducts = new ArrayList<>();
			List<OrderProductDetails> newProducts = new ArrayList<>();
			if (orderProductDetailsRepository.makingInactive(order.getOrderId())) {
				float orderCost = 0.0f;
				for (int i = 0; i < listOfProducts.size(); i++) {
					orderCost = orderCost
							+ (listOfProducts.get(i).getProductQuantity() * this.costOfProduct(listOfProducts.get(i)));

					if (listOfProducts.get(i).getOrderDetailsId() != 0) {
						excistingProducts.add(listOfProducts.get(i));
					} else {
						newProducts.add(listOfProducts.get(i));
					}

				}
				float afterDiscount = ((100 - order.getOrderDiscount()) / 100.0f);
				float finalCost = orderCost * afterDiscount;
				order.setOrderCost(finalCost);
				order.setOrderStatus("PENDING");
				order.setOrderDate(new Date());
				if (ordersRepository.updateOrder(order)) {
					System.out.println(newProducts.toString());
					System.out.println(excistingProducts.toString());
					orderProductDetailsRepository.addProducts(newProducts, order.getOrderId());
					orderProductDetailsRepository.editProducts(excistingProducts, order.getOrderId());
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// WithDraw of Order
	public boolean withDrawOfOrder(Orders order) {
		if (ordersRepository.checkStatus(order)) {
			return ordersRepository.withDrawOfOrder(order);
		}else {
			return false;
		}
		
	}

}
