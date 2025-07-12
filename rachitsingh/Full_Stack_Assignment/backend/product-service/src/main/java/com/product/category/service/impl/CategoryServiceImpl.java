package com.product.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.category.dao.CategoryDAO;
import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.category.service.CategoryService;
import com.product.enums.RequestStatus;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDAO categoryDAO;

	private final List<Category> categoryList = new ArrayList<>();
    private final List<CategoryRequest> categoryRequestList = new ArrayList<>();
    
        @Override
        public void createCategoryRequestService(CategoryRequest categoryRequest) {
            if (categoryRequest == null) {
                throw new IllegalArgumentException("Category request must not be null.");
            }
            if (categoryRequest.getCategoryName() == null || categoryRequest.getCategoryName().trim().isEmpty()) {
                throw new IllegalArgumentException("Category name must not be null or empty.");
            }
            
            categoryDAO.createCategoryRequest(categoryRequest);
        }

        @Override
        public void approveCategoryRequestService(List<Long> requestIds, Integer approvedBy, RequestStatus status) {
            if (requestIds == null || requestIds.isEmpty()) {
                throw new IllegalArgumentException("Request IDs must not be null or empty.");
            }
            if (approvedBy == null || approvedBy <= 0) {
                throw new IllegalArgumentException("ApprovedBy must be a valid user ID.");
            }
            categoryDAO.updateCategoryRequest(requestIds, approvedBy, status);
        }

        @Override
        public List<CategoryRequest> getCategoryRequestService(CategoryRequestSearchCriteria searchCriteria) {
            if (searchCriteria == null) {
                throw new IllegalArgumentException("Search criteria must not be null.");
            }
            
            return categoryDAO.getRequest(searchCriteria);
        }

        @Override
        public void createCategoryService(Category category) {
            if (category == null) {
                throw new IllegalArgumentException("Category must not be null.");
            }
            if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
                throw new IllegalArgumentException("Category name must not be null or empty.");
            }
            
            categoryDAO.createCategory(category);
        }

        @Override
        public List<Category> getCategoryService(CategorySearchCriteria searchCriteria) {
            if (searchCriteria == null) {
                throw new IllegalArgumentException("Search criteria must not be null.");
            }
            
            return categoryDAO.get(searchCriteria);
        }
 }
