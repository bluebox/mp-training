package com.product.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.category.domain.CategoryProductMappingRequest;

@Service
public interface CategoryProductMappingRequestService {
	
	void createCategoryProductMappingRequest(CategoryProductMappingRequest categoryProductMappingRequest);
	
	//change to add multiple
	void updateMappingRequestStatus(Integer mappingRequestId, Character status);
	
	List<CategoryProductMappingRequest> getAllCategoryMappingRequest();
	
	}
