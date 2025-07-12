package com.product.category.service;

import java.util.List;

import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.enums.RequestStatus;

public interface CategoryService {

	void createCategoryRequestService(CategoryRequest categoryRequest);

	void approveCategoryRequestService(List<Long> requestIds, Integer approvedBy, RequestStatus status);

	List<CategoryRequest> getCategoryRequestService(CategoryRequestSearchCriteria searchCriteria);
	
	void createCategoryService(Category category);

	List<Category> getCategoryService(CategorySearchCriteria searchCriteria);

}
