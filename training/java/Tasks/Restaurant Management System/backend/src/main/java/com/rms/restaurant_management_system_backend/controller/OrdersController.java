package com.rms.restaurant_management_system_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.AddOrderRequest;
import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.service.OrdersService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/orders")
public class OrdersController {

	private final OrdersService ordersService;

	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@PostMapping("/addOrder")
	public ResponseEntity<CustomResponse> addOrder(@Valid @RequestBody AddOrderRequest payload) {
		try {
			String name = payload.getName();
			String phone = payload.getPhone();
			int waiterId = payload.getWaiterId();
			double totalPrice = payload.getTotalPrice();
			
//			String errorMessage = validate(name, phone);
//			if(errorMessage != null) {
//				return ResponseEntity.ok(new CustomResponse(false, errorMessage, null));
//			}
//			int id = ordersService.addOrder(name, phone, waiterId);
			List<OrderDetails> orderDetailsList = payload.getOrderDetailsList();
//			return ResponseEntity.ok(new CustomResponse(true, "Order created successfully", id));
			Orders newOrder = ordersService.addOrder(name, phone, waiterId, totalPrice, orderDetailsList);
			return ResponseEntity.ok(new CustomResponse(true, "Order created successfully", newOrder));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new CustomResponse(false, e.getMessage(), null));
		}
	}

	private String validate(String name, String phone) {
		if(name == null)
			return "Name is Empty";
		if(phone == null)
			return "Name is Empty";
		return null;
	}

	@PutMapping("/updateAmount/{OrderId}")
	public ResponseEntity<CustomResponse> updateAmount(@PathVariable int OrderId) {
		if(OrderId <= 0) return new ResponseEntity<>(new CustomResponse(false, "Order Id should be positive", null), HttpStatus.BAD_REQUEST);
		ordersService.updateAmount(OrderId);
		return ResponseEntity.ok(new CustomResponse(true, "Amount updated successfully", OrderId));
	}

	@PutMapping("/updateStatus")
	public ResponseEntity<CustomResponse> updateStatus(@RequestParam int orderId, @RequestParam String status) {
		if(orderId <= 0) return new ResponseEntity<>(new CustomResponse(false, "Order Id should be positive", null), HttpStatus.BAD_REQUEST);
		else if(status.trim().isEmpty()) return new ResponseEntity<>(new CustomResponse(false, "Status should not be empty", null), HttpStatus.BAD_REQUEST);
		ordersService.updateStatus(orderId, status);
		return ResponseEntity.ok(new CustomResponse(true, "Order status updated successfully", status));
	}

	@GetMapping("/allOrders")
	public ResponseEntity<CustomResponse> getAllOrders(@RequestHeader(value = "invocationFrom", required = false) String invocationFrom) {
		System.out.println("Request came from: " + invocationFrom);
		List<Orders> orders = ordersService.getAllOrders();
		if (orders.size() == 0) {
			return ResponseEntity.ok(new CustomResponse(true, "No orders found", orders));
		}
		return ResponseEntity.ok(new CustomResponse(true, "Orders fetched successfully", orders));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomResponse> getOrderById(@PathVariable int id) {
		if(id <= 0) return new ResponseEntity<>(new CustomResponse(false, "Order Id should be positive", null), HttpStatus.BAD_REQUEST);
		Orders order = ordersService.getOrderById(id);
		return ResponseEntity.ok(new CustomResponse(true, "Order fetched successfully", order));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<CustomResponse> getOrdersByCategory(@PathVariable String category) {
		if(category.trim().isEmpty()) return new ResponseEntity<>(new CustomResponse(false, "Category should not be empty", null), HttpStatus.BAD_REQUEST);
		List<Orders> pending = ordersService.getOrdersByCategory(category);
		return ResponseEntity.ok(new CustomResponse(true, category + " orders fetched successfully", pending));
	}

}
