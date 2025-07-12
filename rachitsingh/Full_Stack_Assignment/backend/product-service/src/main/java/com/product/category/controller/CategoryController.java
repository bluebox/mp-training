package com.product.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.category.service.CategoryService;
import com.product.enums.RequestStatus;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/hii")
    public String khj() {
    	return "lsdkjfd";
    }

    @PostMapping("/request")
    public ResponseEntity<String> createCategoryRequest(@RequestBody CategoryRequest categoryRequest) {
        try {
            categoryService.createCategoryRequestService(categoryRequest);
            return ResponseEntity.ok("Category request created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/request/update")
    public ResponseEntity<String> updateCategoryRequest(
            @RequestParam List<Long> requestIds, 
            @RequestParam Integer approvedBy, 
            @RequestParam RequestStatus status) {
        try {
            categoryService.approveCategoryRequestService(requestIds, approvedBy, status);
            return ResponseEntity.ok("Category request(s) approved successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/request/search")
    public ResponseEntity<List<CategoryRequest>> getCategoryRequest(
            @RequestBody CategoryRequestSearchCriteria searchCriteria) {
        try {
            List<CategoryRequest> categoryRequests = categoryService.getCategoryRequestService(searchCriteria);
            return ResponseEntity.ok(categoryRequests);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
  
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try {
            categoryService.createCategoryService(category);
            return ResponseEntity.ok("Category created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> getCategory(
            @RequestBody CategorySearchCriteria searchCriteria) {
        try {
            List<Category> categories = categoryService.getCategoryService(searchCriteria);
            return ResponseEntity.ok(categories);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

