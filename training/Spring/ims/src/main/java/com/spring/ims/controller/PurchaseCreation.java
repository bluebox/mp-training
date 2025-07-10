package com.spring.ims.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.OrderProductDetails;
import com.spring.ims.service.PurchaseCreationService;

@Controller
@RequestMapping("/creation")
public class PurchaseCreation {

	private PurchaseCreationService purchaseCreationService;

	public PurchaseCreation(PurchaseCreationService purchaseCreationService) {
		this.purchaseCreationService = purchaseCreationService;
	}

	@PostMapping("/suppliers")
	public ResponseEntity<?> getSuppliers() {
		return ResponseEntity.ok(purchaseCreationService.listOfSuppliers());
	}

	@PostMapping("/suppliers-products")
	public ResponseEntity<?> sendProducts(@RequestBody List<String> searchCriteria) {
		try {
			if(searchCriteria.get(0).length()==0) {
				return ResponseEntity.ok(Collections.EMPTY_LIST);
			}
			return ResponseEntity.ok(purchaseCreationService.listOfProducts(searchCriteria));
		} catch (Exception e) {
			System.out.println("Error at sendProducts in PurchaseCreation : " + e.getMessage());
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping("/suppliers-products-cost")
	public ResponseEntity<?> costOfProducts(@RequestBody OrderProductDetails orderProductDetails) {
		try {
			System.out.println(orderProductDetails.toString());
			return ResponseEntity.ok(purchaseCreationService.costOfProduct(orderProductDetails));
		} catch (Exception e) {
			System.out.println("Error at sendProducts in PurchaseCreation : " + e.getMessage());
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping("/purchase-created")
	public ResponseEntity<?> purcahseCreation(@RequestBody FullOrder fullOrder) {
		try {
			if (purchaseCreationService.creationOfOrder(fullOrder)) {
				Map<String, String> successResponse = new HashMap<>();
				successResponse.put("status", "success");
				successResponse.put("message", "Purchase Crated");
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

}
