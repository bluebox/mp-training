package com.product.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.category.domain.CategoryProductMapping;
import com.product.category.service.CategoryProductMappingService;
import com.product.domain.Product;
import com.product.dto.ApiResponse;
import com.product.util.ResponseUtil;


@RestController
@RequestMapping("/api/category-product-mappings")
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowCredentials = "true"
	)
public class CategoryProductMappingRestController {

    private final CategoryProductMappingService categoryProductMappingService;

    @Autowired
    public CategoryProductMappingRestController(CategoryProductMappingService categoryProductMappingService) {
        this.categoryProductMappingService = categoryProductMappingService;
    }
    
    @PostMapping("/addmappings")
    public ResponseEntity<ApiResponse<List<CategoryProductMapping>>> addCategoryProductMappings(@RequestBody List<CategoryProductMapping> categoryProductMappings) {
        try {
            categoryProductMappingService.addCategoryProductMappingService(categoryProductMappings);
            return ResponseUtil.success("Category-Product mappings created successfully", categoryProductMappings);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to create category-product mappings: " + e.getMessage(), null));
        }
    }

    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByCategoryId(@PathVariable Integer categoryId) {
        try {
            List<Product> products = categoryProductMappingService.getAllProductsByCategoryId(categoryId);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "No products found for the specified category", null));
            }
            return ResponseUtil.success("Products retrieved successfully", products);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Failed to retrieve products: " + e.getMessage(), null));
        }
    }
}
