package com.product.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.category.domain.CategoryProductMappingRequest;
import com.product.category.service.CategoryProductMappingRequestService;
import com.product.dto.ApiResponse;
import com.product.dto.UpdateMappingRequestStatusRequest;
import com.product.enums.RequestStatus;
import com.product.util.ResponseUtil;

@RestController
@RequestMapping("/api/category-product-mapping-requests") 
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowCredentials = "true"
	)
public class CategoryProductMapRequestRestController {

    private final CategoryProductMappingRequestService categoryProductMappingRequestService;

    @Autowired
    public CategoryProductMapRequestRestController(
            CategoryProductMappingRequestService categoryProductMappingRequestService) {
        this.categoryProductMappingRequestService = categoryProductMappingRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CategoryProductMappingRequest>> createCategoryProductMappingRequest(
            @RequestBody CategoryProductMappingRequest mappingRequest) {
        try {
            categoryProductMappingRequestService.createCategoryProductMappingRequest(mappingRequest);
        	System.out.println(mappingRequest);
            return ResponseUtil.success("Category-Product mapping request created successfully", mappingRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to create mapping request: " + e.getMessage(), null));
        }
    }

    @PostMapping("/update-status")
    public ResponseEntity<ApiResponse<String>> updateMappingRequestStatus(
            @RequestBody UpdateMappingRequestStatusRequest request) {
        try {
            categoryProductMappingRequestService.updateMappingRequestStatusService(
                    request.getRequestIds(),
                    request.getApprovedBy(),
                    request.getStatus()
            );
            return ResponseUtil.success("Mapping request status updated successfully", null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to update mapping request status: " + e.getMessage(), null));
        }
    }


    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CategoryProductMappingRequest>>> getMappingRequests(
            @RequestParam(required = false) List<Long> requestIds,
            @RequestParam(required = false) RequestStatus status,
            @RequestParam(required = false) Long loggedInUser) {
        try {
//            if ((requestIds == null || requestIds.isEmpty()) && status == null && loggedInUser == null) {
//                return ResponseEntity.badRequest()
//                        .body(new ApiResponse<>(false, "At least one filter (requestIds, status, loggedInUser) must be provided", null));
//            }
            
            List<CategoryProductMappingRequest> requests = categoryProductMappingRequestService
                    .getCategoryMappingRequest(requestIds, status, loggedInUser);
            
            if (requests.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "No mapping requests found for the given criteria", null));
            }
            
            return ResponseUtil.success("Mapping requests retrieved successfully", requests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to retrieve mapping requests: " + e.getMessage(), null));
        }
    }
}
