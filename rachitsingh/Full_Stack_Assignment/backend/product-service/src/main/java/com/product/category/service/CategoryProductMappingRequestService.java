package com.product.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.category.domain.CategoryProductMappingRequest;
import com.product.enums.RequestStatus;

@Service
public interface CategoryProductMappingRequestService {
	
	void createCategoryProductMappingRequest(CategoryProductMappingRequest categoryProductMappingRequest);
	
	void updateMappingRequestStatusService(List<Long> requestIds, Integer approvedBy, RequestStatus status);
	
	List<CategoryProductMappingRequest> getCategoryMappingRequest(List<Long> requestIds,RequestStatus requestStatus,Long loggedInUser) throws Exception;
	
	}
