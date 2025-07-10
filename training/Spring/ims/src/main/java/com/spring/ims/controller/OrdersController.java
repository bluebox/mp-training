package com.spring.ims.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.Orders;
import com.spring.ims.service.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	private OrdersService ordersService;
	
	@Autowired
	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@PostMapping("/pending-orders")
	public ResponseEntity<?> getPendingOrders() {
		try {
			return ResponseEntity.ok(ordersService.pendingOrders());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	// AllOrders
	@PostMapping("/get-orders")
	public ResponseEntity<?> getOrders() {
		try {
			return ResponseEntity.ok(ordersService.allOrders());
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	// view Order
	@PostMapping("/view-order")
	public ResponseEntity<?> viewOrder(@RequestBody Orders order) {
		try {
			System.out.println(order.toString());
			System.out.println(ordersService.productsOfOrder(order));
			return ResponseEntity.ok(ordersService.productsOfOrder(order));
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	// editing a purchase
	@PostMapping("/edit-purchase")
	public ResponseEntity<?> editPurchase(@RequestBody FullOrder fullOrder) {
		try {
			if (ordersService.editingOfOrder(fullOrder)) {
				Map<String, String> successResponse = new HashMap<>();
				successResponse.put("status", "success");
				successResponse.put("message", "Purchase Edited");
				return ResponseEntity.ok(successResponse);
			} else {
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "error");
				errorResponse.put("message", "Somethig WentWrong");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
			}
		} catch (Exception e) {
			System.out.println("Error at sendProducts in PurchaseCreation : " + e.getMessage());
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	//withDraw Order
	@PostMapping("/withdraw-order")
	public ResponseEntity<?> withdrawOrder(@RequestBody Orders order) {
		try {
			return ResponseEntity.ok(ordersService.withDrawOfOrder(order));
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
