package com.consumerExample.orders.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.consumerExample.orders.utilities.CustomResponse;
import com.consumerExample.orders.utilities.OrdersFeignClient;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrdersController {

	private final RestTemplate restTemplate;
	private final WebClient webClient;
	private final OrdersFeignClient feignClient;

	@GetMapping("/fetchOrdersRest")
	public ResponseEntity<CustomResponse> fetchOrdersRest(@RequestHeader("Authorization") String authHeader) {
		String uri = "http://localhost:8080/api/orders/allOrders";
		HttpHeaders headers = new HttpHeaders();
		headers.add("invocationFrom", "RestTemplate");
		headers.set("Authorization", authHeader);
		HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
		return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, CustomResponse.class);
	}

	@GetMapping("/fetchOrdersWeb")
	public ResponseEntity<CustomResponse> fetchOrdersWeb(@RequestHeader("Authorization") String authHeader) {
		return webClient.get()
				.uri("http://localhost:8080/api/orders/allOrders")
				.header("invocationFrom", "WebClient")
				.header("Authorization", authHeader)
				.retrieve()
				.toEntity(CustomResponse.class)
				.block();
	}

	@GetMapping("/fetchOrdersFeign")
	public ResponseEntity<CustomResponse> fetchOrdersFeign(@RequestHeader("Authorization") String authHeader) {
		return feignClient.getAllOrders(authHeader, "FeignClient");
	}
	
	

	@PostMapping("/createOrderRest")
	public ResponseEntity<CustomResponse> createOrderRest(@RequestHeader("Authorization") String authHeader, @RequestBody Object payload) {
		String uri = "http://localhost:8080/api/orders/addOrder";
		HttpHeaders headers = new HttpHeaders();
		headers.add("invocationFrom", "RestTemplate");
		headers.set("Authorization", authHeader);
		HttpEntity<Object> entity = new HttpEntity<>(payload, headers);
		return restTemplate.exchange(uri, HttpMethod.POST, entity, CustomResponse.class);
	}

	@PostMapping("/createOrderWeb")
	public ResponseEntity<CustomResponse> createOrderWeb(@RequestHeader("Authorization") String authHeader, @RequestBody Object payload) {
		return webClient.post()
				.uri("http://localhost:8080/api/orders/addOrder")
				.header("invocationFrom", "WebClient")
				.header("Authorization", authHeader)
				.bodyValue(payload)
				.retrieve()
				.toEntity(CustomResponse.class)
				.block();
	}

	@PostMapping("/createOrderFeign")
	public ResponseEntity<CustomResponse> createOrderFeign(@RequestHeader("Authorization") String authHeader, @RequestBody Object payload) {
		return feignClient.addOrder(authHeader, "FeignClient", payload);
	}
}
