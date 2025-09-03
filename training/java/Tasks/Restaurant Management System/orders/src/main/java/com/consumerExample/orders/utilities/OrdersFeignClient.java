package com.consumerExample.orders.utilities;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "orders-service", url = "http://localhost:8080/api/orders")
public interface OrdersFeignClient {

	@GetMapping("/allOrders")
	ResponseEntity<CustomResponse> getAllOrders(@RequestHeader("Authorization") String authHeader,
			@RequestHeader("invocationFrom") String invocationFrom);

	@PostMapping("/addOrder")
	ResponseEntity<CustomResponse> addOrder(@RequestHeader("Authorization") String authHeader,
			@RequestHeader("invocationFrom") String invocationFrom, @RequestBody Object payload);
}
