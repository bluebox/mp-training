package com.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;
import com.product.service.ProductRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product-requests")
@RequiredArgsConstructor
public class ProductRequestController {
	private final ProductRequestService productRequestService;

	@PostMapping("/create")
	public ProductRequest createProductRequest(@RequestBody ProductRequest request) {
		return productRequestService.generateProductRequest(request);
	}

	@PostMapping("/search")
	public List<ProductRequest> getRequestByCriteria(@RequestBody SearchProductRequestCriteria criteria) {
		return productRequestService.getRequestsByCriteria(criteria);
	}

	@PutMapping("/update")
	public String updatePendingRequest(@RequestBody ProductRequest requestToUpdate) {
		productRequestService.updatePendingProductRequest(requestToUpdate);
		return "Product request " + "[ID:" + requestToUpdate.getProductRequestId() + "]" + " updated successfully";
	}
}
