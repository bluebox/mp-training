package com.product.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.Exceptions.ProductDatabaseOperationException;
import com.product.domain.ApprovalActionRequest;
import com.product.domain.Product;
import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;
import com.product.service.ProductApprovalService;
import com.product.service.ProductService;

@RestController
@RequestMapping("/api/product-approval")
public class ProductApprovalController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductApprovalService productApprovalService;

	@GetMapping("/products")
	public List<Product> getAllApprovedProducts() {
		return productService.getProducts("");
	}

	@GetMapping("/pending-requests")
	public List<ProductRequest> getPendingRequests() {
		return productApprovalService.getRequests(null, Collections.singletonList(RequestStatus.PENDING));
	}

	@PostMapping("/approve")
	public ResponseEntity<String> approveRequests(@RequestBody List<ApprovalActionRequest> requests) throws ProductDatabaseOperationException {
//		Long managerId = 4L;
		for (ApprovalActionRequest req : requests) {
			if (req.getProductRequestIds() == null || req.getProductRequestIds().isEmpty()) {
				throw new IllegalArgumentException("ProductRequestIds must not be null or empty.");
			}
			if (req.getManagerId() == null) {
				throw new IllegalArgumentException("ManagerId must not be null.");
			}

			productApprovalService.approveRequest(req.getProductRequestIds(),  RequestStatus.APPROVED,
					req.getManagerId());
		}
//		productApprovalService.approveRequest(productRequestIds, RequestStatus.APPROVED, managerId);
		return ResponseEntity.ok("Approved successfully");
	}

	@PostMapping("/reject")
	public ResponseEntity<String> rejectRequests(@RequestBody List<ApprovalActionRequest> requests) {
		try {
			for (ApprovalActionRequest req : requests) {
				if (req.getProductRequestIds() == null || req.getProductRequestIds().isEmpty()) {
					throw new IllegalArgumentException("ProductRequestIds must not be null or empty.");
				}
				if (req.getManagerId() == null) {
					throw new IllegalArgumentException("ManagerId must not be null.");
				}

				productApprovalService.rejectRequest(req.getProductRequestIds(), RequestStatus.DECLINED,
						req.getManagerId());
			}
			return ResponseEntity.ok("Rejected successfully");
		} catch (Exception e) {
			e.printStackTrace(); // See full error in console
			return ResponseEntity.status(500).body("Rejection failed: " + e.getMessage());
		}
	}

}
