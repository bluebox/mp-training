package com.product.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.category.domain.CategoryProductMapping;
import com.product.category.service.CategoryProductMappingService;
import com.product.domain.Product;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/category-product-mappings")
public class CategoryProductMappingRestController {

    private final CategoryProductMappingService categoryProductMappingService;

    @Autowired
    public CategoryProductMappingRestController(CategoryProductMappingService categoryProductMappingService) {
        this.categoryProductMappingService = categoryProductMappingService;
    }
    
    @GetMapping("")
    public String getMethodName() {
    	System.out.println("Hwllo");
        return "hello this isme";
    }
    

    @PostMapping("/addmappings")
    public ResponseEntity<?> addCategoryProductMappings(@RequestBody List<CategoryProductMapping> categoryProductMappings) {
        try {
            categoryProductMappingService.addCategoryProductMappingService(categoryProductMappings);
            return new ResponseEntity<>("Category-Product mappings created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create category-product mappings: " + e.getMessage(), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable Integer categoryId) {
        try {
            List<Product> products = categoryProductMappingService.getAllProductsByCategoryId(categoryId);
            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found for the specified category", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve products: " + e.getMessage(), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
