package com.spring.InventryManagementSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.InventryManagementSystem.domain.OrderDetails;
import com.spring.InventryManagementSystem.interfaces.service.OrderServiceInterface;

@RestController
@RequestMapping("order")
public class OrderController {

	private OrderServiceInterface orderService;

	public OrderController(OrderServiceInterface orderService) {
		this.orderService = orderService;
	}

	@GetMapping("get-all-suppliers")
	public ResponseEntity<?> getAllSuppliers() throws Exception {
		return ResponseEntity.ok(orderService.getAllSuppliers());
	}

	@GetMapping("get-all-products-of-supplier")
	public ResponseEntity<?> getAllProducts(@RequestParam String supplier) throws Exception {
		return ResponseEntity.ok(orderService.getAllProducts(supplier));
	}

	@PostMapping("purchase-ceation")
	public ResponseEntity<?> purchaseCreation(@RequestBody OrderDetails orderDetails) throws Exception {
		if(orderService.purchaseCreation(orderDetails)) {
			return ResponseEntity.ok("Success");
		}else {
			return ResponseEntity.internalServerError().body("Failure");
		}
	}
	
	@GetMapping("withdraw-order")
	public ResponseEntity<?> withdrawOrder(@RequestParam Integer orderId) throws Exception {
		if(orderService.withdrawOrder(orderId)) {
			return ResponseEntity.ok("Success");
		}else {
			return ResponseEntity.internalServerError().body("This Action Cannot Be Done");
		}
	}
	
	@PostMapping("order-status-update")
	public ResponseEntity<?> orderStatusUpdate(@RequestBody OrderDetails orderDetails) throws Exception {
		if(orderService.orderStatusUpdate(orderDetails)) {
			return ResponseEntity.ok("Success");
		}else {
			return ResponseEntity.internalServerError().body("This Action Cannot Be Done");
		}
	}
	
	@PostMapping("view-order")
	public ResponseEntity<?> getAllProducts(@RequestBody OrderDetails orderDetails) throws Exception {
		return ResponseEntity.ok(orderService.viewOrder(orderDetails));
	}

}
