package com.rms.restaurant_management_system_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.AddOrderRequest;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.service.OrdersService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

@RestController
@RequestMapping("api/orders")
public class OrdersController {

	private final OrdersService ordersService;

	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@PostMapping("/addOrder")
	public ResponseEntity<CustomResponse> addOrder(@RequestBody AddOrderRequest payload) {
		try {
			String name = payload.getName();
			String phone = payload.getPhone();
			int waiterId = payload.getWaiterId();
			double totalPrice = payload.getTotalPrice();
//			int id = ordersService.addOrder(name, phone, waiterId);
			List<OrderDetails> orderDetailsList = payload.getOrderDetailsList();
//			return ResponseEntity.ok(new CustomResponse(true, "Order created successfully", id));
			Orders newOrder = ordersService.addOrder(name, phone, waiterId, totalPrice, orderDetailsList);
			return ResponseEntity.ok(new CustomResponse(true, "Order created successfully", newOrder));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new CustomResponse(false, e.getMessage(), null));
		}
	}

	@PutMapping("/updateAmount/{OrderId}")
	public ResponseEntity<CustomResponse> updateAmount(@PathVariable int OrderId) {
		ordersService.updateAmount(OrderId);
		return ResponseEntity.ok(new CustomResponse(true, "Amount updated successfully", OrderId));
	}

	@PutMapping("/updateStatus")
	public ResponseEntity<CustomResponse> updateStatus(@RequestParam int orderId, @RequestParam String status) {
		ordersService.updateStatus(orderId, status);
		return ResponseEntity.ok(new CustomResponse(true, "Order status updated successfully", status));
	}

	@GetMapping("/allOrders")
	public ResponseEntity<CustomResponse> getAllOrders() {
		List<Orders> orders = ordersService.getAllOrders();
		if (orders.size() == 0) {
			return ResponseEntity.ok(new CustomResponse(true, "No orders found", orders));
		}
		return ResponseEntity.ok(new CustomResponse(true, "Orders fetched successfully", orders));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomResponse> getOrderById(@PathVariable int id) {
		Orders order = ordersService.getOrderById(id);
		return ResponseEntity.ok(new CustomResponse(true, "Order fetched successfully", order));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<CustomResponse> getOrdersByCategory(@PathVariable String category) {
		List<Orders> pending = ordersService.getOrdersByCategory(category);
		return ResponseEntity.ok(new CustomResponse(true, category + " orders fetched successfully", pending));
	}

}
