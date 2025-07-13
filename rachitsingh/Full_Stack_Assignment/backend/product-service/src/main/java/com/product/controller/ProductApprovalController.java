package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.dao.ProductRequestDAO;
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
        return productApprovalService.getRequests(null, List.of(RequestStatus.PENDING));
    }

    @PostMapping("/approve")
    public String approveRequests(@RequestBody ApprovalActionRequest request) {
        productApprovalService.updateRequest(
                request.getProductRequestIds(),
                RequestStatus.APPROVED,
                request.getManagerId()
        );
        return "Approved successfully";
    }

    @PostMapping("/reject")
    public String rejectRequests(@RequestBody ApprovalActionRequest request) {
        productApprovalService.updateRequest(
                request.getProductRequestIds(),
                RequestStatus.DECLINED,
                request.getManagerId()
        );
        return "Rejected successfully";
    }
}