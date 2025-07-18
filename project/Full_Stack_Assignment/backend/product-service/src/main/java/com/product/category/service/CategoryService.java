package com.product.category.service;

import java.util.List;

import com.product.Exceptions.CategoryDatabaseOperationException;
import com.product.Exceptions.CategoryRequestDatabaseOperationException;
import com.product.Exceptions.CategoryRequestNotFoundException;
import com.product.Exceptions.InvalidCategoryRequestException;
import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.enums.RequestStatus;


public interface CategoryService {

	void createCategoryRequestService(CategoryRequest categoryRequest) 
			throws InvalidCategoryRequestException, CategoryRequestDatabaseOperationException;
	
	void processCategoryRequest(List<Long> requestIds, Integer approvedBy, RequestStatus status) 
			throws CategoryRequestNotFoundException, CategoryRequestDatabaseOperationException;

	void updateCategoryRequestService(List<Long> requestIds, Integer approvedBy, RequestStatus status)
			throws CategoryRequestNotFoundException, CategoryRequestDatabaseOperationException;

	List<CategoryRequest> getCategoryRequestService(CategoryRequestSearchCriteria searchCriteria) 
			throws CategoryRequestNotFoundException,CategoryRequestDatabaseOperationException;
	
	void createCategoryService(Category category)
			throws CategoryDatabaseOperationException;

	List<Category> getCategoryService(CategorySearchCriteria searchCriteria) 
			throws CategoryDatabaseOperationException;

}
