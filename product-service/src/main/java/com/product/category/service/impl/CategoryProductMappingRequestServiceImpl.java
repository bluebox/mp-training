package com.product.category.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.category.dao.CategoryProductMappingRequestDAO;
import com.product.category.domain.CategoryProductMapping;
import com.product.category.domain.CategoryProductMappingRequest;
import com.product.category.service.CategoryProductMappingRequestService;
import com.product.category.service.CategoryProductMappingService;
import com.product.enums.RequestStatus;

@Service
public class CategoryProductMappingRequestServiceImpl implements CategoryProductMappingRequestService {

    private final CategoryProductMappingRequestDAO categoryProductMappingRequestDAO;
    private final CategoryProductMappingService categoryProductMappingService;

    @Autowired
    public CategoryProductMappingRequestServiceImpl(
            CategoryProductMappingRequestDAO categoryProductMappingRequestDAO,
            CategoryProductMappingService categoryProductMappingService) {
        this.categoryProductMappingRequestDAO = categoryProductMappingRequestDAO;
        this.categoryProductMappingService = categoryProductMappingService;
    }

    @Override
    public void createCategoryProductMappingRequest(CategoryProductMappingRequest categoryProductMappingRequest) {
        if (categoryProductMappingRequest == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (categoryProductMappingRequest.getCategoryId() == null || categoryProductMappingRequest.getCategoryId() <= 0) {
            throw new IllegalArgumentException("CategoryId must be provided and positive");
        }
        if (categoryProductMappingRequest.getProductId() == null || categoryProductMappingRequest.getProductId() <= 0) {
            throw new IllegalArgumentException("ProductId must be provided and positive");
        }
        if (categoryProductMappingRequest.getRequestedBy() == null || categoryProductMappingRequest.getRequestedBy() <= 0) {
            throw new IllegalArgumentException("RequestedBy must be provided and positive");
        }
        categoryProductMappingRequestDAO.createMappingRequest(categoryProductMappingRequest);
    }

    @Override
    public void updateMappingRequestStatusService(List<Long> requestIds, Integer approvedBy, RequestStatus status) {
        if (requestIds == null || requestIds.isEmpty()) {
            throw new IllegalArgumentException("RequestIds must not be null or empty");
        }
        if (approvedBy == null || approvedBy <= 0) {
            throw new IllegalArgumentException("ApprovedBy must be provided and positive");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }
        
        categoryProductMappingRequestDAO.updateRequestStatus(requestIds, approvedBy, status);
        
        if (RequestStatus.APPROVED.equals(status)) {
            try {
                
                List<CategoryProductMappingRequest> approvedRequests = getCategoryMappingRequest(requestIds, RequestStatus.APPROVED, null);
                
                if (approvedRequests != null && !approvedRequests.isEmpty()) {
                    List<CategoryProductMapping> mappings = convertRequestsToMappings(approvedRequests);
                    
                    categoryProductMappingService.addCategoryProductMappingService(mappings);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error processing approved mapping requests", e);
            }
        }
    }
    
    private List<CategoryProductMapping> convertRequestsToMappings(List<CategoryProductMappingRequest> requests) {
        List<CategoryProductMapping> mappings = new ArrayList<>();
        
        for (CategoryProductMappingRequest request : requests) {
            CategoryProductMapping mapping = new CategoryProductMapping();
            mapping.setProductId(request.getProductId());
            mapping.setCategoryId(request.getCategoryId());
            mapping.setCreatedAtDate(LocalDateTime.now());
            mappings.add(mapping);
        }
        
        return mappings;
    }

    @Override
    public List<CategoryProductMappingRequest> getCategoryMappingRequest(List<Long> requestIds, RequestStatus requestStatus, Long loggedInUser) throws Exception {
        if ((requestIds == null || requestIds.isEmpty()) && requestStatus == null && loggedInUser == null) {
            throw new IllegalArgumentException("At least one filter (requestIds, requestStatus, loggedInUser) must be provided");
        }
        return categoryProductMappingRequestDAO.findAllRequest(requestIds, requestStatus, loggedInUser);
    }
}
