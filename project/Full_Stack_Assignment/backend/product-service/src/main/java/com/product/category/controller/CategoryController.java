package com.product.category.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.Exceptions.CategoryDatabaseOperationException;
import com.product.Exceptions.CategoryRequestDatabaseOperationException;
import com.product.Exceptions.CategoryRequestNotFoundException;
import com.product.Exceptions.InvalidCategoryRequestException;
import com.product.category.domain.Category;
import com.product.category.domain.CategoryRequest;
import com.product.category.domain.CategoryRequestSearchCriteria;
import com.product.category.domain.CategorySearchCriteria;
import com.product.category.domain.UpdateCategoryRequestPayload;
import com.product.category.service.CategoryService;

@RestController
@RequestMapping("/categories")

@CrossOrigin(origins = "http://localhost:5173") 
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @PreAuthorize("hasRole('ROLE_CATEGORY_EXE')")
    @PostMapping("/request")
    public ResponseEntity<String> createCategoryRequest(@RequestBody CategoryRequest categoryRequest) {
        try {
            try {
				categoryService.createCategoryRequestService(categoryRequest);
			} catch (InvalidCategoryRequestException e) {
				e.printStackTrace();
			} catch (CategoryRequestDatabaseOperationException e) {
				e.printStackTrace();
			}
            return ResponseEntity.ok("Category request created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/request/process")
    public ResponseEntity<String> processCategoryRequest(@RequestBody UpdateCategoryRequestPayload payload) {
        try {
            System.out.println("Reached backend");
            categoryService.processCategoryRequest(
                payload.getRequestIds(),
                payload.getApprovedBy(),
                payload.getStatus()
            );
        } catch (CategoryRequestNotFoundException | CategoryRequestDatabaseOperationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        }
        return ResponseEntity.ok("Category request(s) processed successfully.");
    }


    
    @PostMapping("/request/update")
    public ResponseEntity<String> updateCategoryRequest(@RequestBody UpdateCategoryRequestPayload payload) {
        try {
            try {
                categoryService.updateCategoryRequestService(
                    payload.getRequestIds(), 
                    payload.getApprovedBy(), 
                    payload.getStatus()
                );
            } catch (CategoryRequestNotFoundException | CategoryRequestDatabaseOperationException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok("Category request(s) approved successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'CATEGORY_EXE')")
    @GetMapping("/request/search")
    public ResponseEntity<List<CategoryRequest>> getCategoryRequestGet(
    		@RequestParam(required = false) Long requestId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String categoryName) {

        try {
        
            CategoryRequestSearchCriteria searchCriteria = new CategoryRequestSearchCriteria();
            System.out.println("created");
            searchCriteria.setRequestId(requestId);
            searchCriteria.setStatus(status);
            System.out.println("status assigned executed");
            searchCriteria.setCategoryName(categoryName);
            System.out.println("cat name");
            List<CategoryRequest> categoryRequests = categoryService.getCategoryRequestService(searchCriteria);
            System.out.println("req feteched");
            return ResponseEntity.ok(categoryRequests);

        } catch (CategoryRequestNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (CategoryRequestDatabaseOperationException e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'CATEGORY_EXE')")
    @PostMapping("/request/search")
    public ResponseEntity<List<CategoryRequest>> getCategoryRequest(
            @RequestBody CategoryRequestSearchCriteria searchCriteria) {
    	List<CategoryRequest> categoryRequests;
            try {
				categoryRequests = categoryService.getCategoryRequestService(searchCriteria);
				return ResponseEntity.ok(categoryRequests);
			} catch (CategoryRequestNotFoundException e) {
				e.printStackTrace();
			} catch (CategoryRequestDatabaseOperationException e) {
				e.printStackTrace();
			}
           
            
            return ResponseEntity.badRequest().body(null);
    }
  
    @PreAuthorize("hasRole('ROLE_CATEGORY_EXE')")
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try {
            try {
				categoryService.createCategoryService(category);
			} catch (CategoryDatabaseOperationException e) {
				e.printStackTrace();
			}
            return ResponseEntity.ok("Category created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'CATEGORY_EXE','USER','PRODUCT_EXE')")
    @PostMapping("/search")
    public ResponseEntity<List<Category>> getCategory(
            @RequestBody CategorySearchCriteria searchCriteria) {
            List<Category> categories;
			try {
				categories = categoryService.getCategoryService(searchCriteria);
				 return ResponseEntity.ok(categories);
			} catch (CategoryDatabaseOperationException e) {
				e.printStackTrace();
				return ResponseEntity.badRequest().body(null);
			}
        }
    
    @PreAuthorize("hasAnyRole('MANAGER', 'CATEGORY_EXE','USER','PRODUCT_EXE')")
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategories(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String status) {
        try {
            CategorySearchCriteria criteria = new CategorySearchCriteria();
            criteria.setCategoryNames(categoryName);
            
            List<Category> categories = categoryService.getCategoryService(criteria);
            return ResponseEntity.ok(categories);
        } catch (CategoryDatabaseOperationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
    }

