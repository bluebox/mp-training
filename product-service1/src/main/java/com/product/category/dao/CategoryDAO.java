package com.product.category.dao;

import java.util.List;

import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.enums.RequestStatus;

public interface CategoryDAO {
	
    void createCategoryRequest(CategoryRequest categoryRequest);

    void updateCategoryRequest(List<Long> requestIds, Integer approvedBy, RequestStatus status);

    List<CategoryRequest> getRequest(CategoryRequestSearchCriteria searchCriteria);
    
    void  createCategory(Category  category);

    List<Category> get(CategorySearchCriteria categorySearchCriteria);

}
