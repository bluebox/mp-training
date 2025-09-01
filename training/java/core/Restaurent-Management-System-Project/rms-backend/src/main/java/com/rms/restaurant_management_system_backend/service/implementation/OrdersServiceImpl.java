package com.rms.restaurant_management_system_backend.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;
import com.rms.restaurant_management_system_backend.dao.OrderDetailsDao;
import com.rms.restaurant_management_system_backend.dao.OrdersDao;
import com.rms.restaurant_management_system_backend.dao.implementation.CustomerDaoImpl;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.service.CustomerService;
import com.rms.restaurant_management_system_backend.service.OrderDetailsService;
import com.rms.restaurant_management_system_backend.service.OrdersService;
import com.rms.restaurant_management_system_backend.service.WaitersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	private final OrdersDao ordersDao;
	private final CustomerService customerService;
	private final OrderDetailsDao orderDetailsDao;
	private final OrderDetailsService orderDetailsService;
	private final WaitersService waitersService;
	private final CustomerDaoImpl customerDao;

	public OrdersServiceImpl(OrdersDao ordersDao, OrderDetailsDao orderDetailsDao, OrderDetailsService orderDetailsService ,WaitersService waitersService,
			CustomerService customerService, CustomerDaoImpl customerDao) {
		this.ordersDao = ordersDao;
		this.customerService = customerService;
		this.orderDetailsDao = orderDetailsDao;
		this.orderDetailsService=orderDetailsService;
		this.waitersService = waitersService;
		this.customerDao = customerDao;
	}

	@Override
	@Transactional
	public Orders addOrder(String name, String phone, int waiterId, double totalPrice, List<OrderDetails> orderDetailsList) {
		int customerId = 0;
		try {
			customerId = customerDao.getCustomerIdByNumber(phone);
		} catch (EmptyResultDataAccessException ex) {
			customerId = customerService.addCustomer(new Customer(name, phone));
		}
		Orders order = new Orders(customerId, waiterId, totalPrice);
		order.setOrderDate(LocalDate.now());
		order.setStatus(OrderStatus.PENDING);
		int orderId=ordersDao.addOrder(order);
		orderDetailsList.stream().forEach(item -> item.setOrderId(orderId));
		orderDetailsService.insertOrderDetails(orderDetailsList);
		order.setOrderId(orderId);
		waitersService.updateWaiterAvailability(waiterId);
		return order;
	}

	@Override
	public void updateStatus(int id, String status) {
		Orders order = ordersDao.getOrderById(id);
		if (order == null) {
			throw new RestaurantOperationException("Order with ID " + order.getOrderId() + " does not exist");
		}
		if (!order.getStatus().equals(OrderStatus.PENDING)) {
			throw new RestaurantOperationException(
					"Cannot update status. Order with ID " + order.getOrderId() + " is already " + order.getStatus().getStatus());
		}
		ordersDao.insertLog(order);
		int rows = ordersDao.updateStatus(order, status);
		if (rows != 1) {
			throw new RestaurantOperationException("Failed to update status with ID " + order.getOrderId());
		}
		waitersService.updateWaiterAvailability(order.getWaiterId());
	}

	@Override
	public void updateAmount(int orderId) {
		if (orderId <= 0) {
			throw new RestaurantOperationException("Invalid inputs");
		}
		Orders order = ordersDao.getOrderById(orderId);
		if (!order.getStatus().equals(OrderStatus.PENDING)) {
			throw new RestaurantOperationException(
					"Cannot update amount. Order with ID " + order.getOrderId() + " is already " + order.getStatus());
		}
		double amount = calculateTotalAmount(orderId);
		if (amount <= 0) {
			throw new RestaurantOperationException("Amount must be greater than zero");
		}
		int rows = ordersDao.updateAmount(order, amount);
		if (rows != 1) {
			throw new RestaurantOperationException("Failed to update amount with ID " + order.getOrderId());
		}
	}

	@Override
	public Orders getOrderById(int id) {
		Orders order = ordersDao.getOrderById(id);
		if (order == null) {
			throw new RestaurantOperationException("Order with ID " + id + " not found");
		}
		return order;
	}

	@Override
	public List<Orders> getAllOrders() {
		List<Orders> orders = ordersDao.getAllOrders();
//		if (orders.isEmpty()) {
//			throw new ResourceNotFoundException("No orders found");
//		}
		return orders;
	}

	@Override
	public List<Orders> getOrdersByCategory(String category) {
		List<Orders> orders = ordersDao.getOrdersByCategory(category);
		if (orders.isEmpty()) {
			throw new RestaurantOperationException("No orders found for category: " + category);
		}
		return orders;
	}

	public double calculateTotalAmount(int id) {
		double amount = 0;
		List<OrderDetails> details = orderDetailsDao.selectAllOrderDetails().stream().filter(d -> d.getOrderId() == id)
				.collect(Collectors.toList());
		for (OrderDetails detail : details) {
			amount += detail.getQuantity() * detail.getPrice();
		}
		return amount;
	}

}
