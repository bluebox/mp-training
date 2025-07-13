package com.orders.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.orders.app.OrderConfiguration;
import com.orders.domain.Order;
import com.orders.domain.SearchOrderCriteria;
import com.orders.enums.OrderStatus;
import com.orders.exceptions.InvalidOrderException;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.item.domain.OrderItem;
import com.orders.service.OrderService;

@SpringBootTest(classes = OrderConfiguration.class)
public class TestingOrderService {
	@Autowired
	private OrderService orderService;

	@Test
	public void test() throws OrderNotFoundException, OrderDatabaseOperationException, InvalidOrderException {
		OrderItem item1 = new OrderItem(null, null, 501L, "Bluetooth Speaker", 1, BigDecimal.valueOf(1040.25),
				LocalDateTime.now());
		OrderItem item2 = new OrderItem(null, null, 502L, "Wireless Mouse", 2, BigDecimal.valueOf(500.13),
				LocalDateTime.now());

		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(item1);
		orderItems.add(item2);

		Order testOrder = new Order(null, 4L, "Gachibowli, Hyderabad", BigDecimal.valueOf(2040.51),
				OrderStatus.PROCESSING, LocalDateTime.now(), LocalDateTime.now(), orderItems);

		Order placedOrder = orderService.placeNewOrder(testOrder);
		System.out.println("Placed Order: " + placedOrder);
		List<OrderStatus> statuses = new ArrayList<>();
		statuses.add(OrderStatus.PROCESSING);
		statuses.add(OrderStatus.CANCELLED);

		SearchOrderCriteria criteria = new SearchOrderCriteria();
		criteria.setCustomerId(4L);
		criteria.setOrderStatuses(statuses);
		criteria.setFromDate(LocalDateTime.now().minusDays(30));
		criteria.setToDate(LocalDateTime.now());
		criteria.setPageNumber(0);
		criteria.setPageSize(10);

		List<Order> orders = orderService.getMyOrders(criteria);

		for (Order order : orders) {
			System.out.println("Order ID: " + order.getOrderId());
			System.out.println("Order Items: " + order.getOrderItems());
		}
	}
}