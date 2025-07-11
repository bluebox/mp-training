package com.product.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.category.domain.CategoryProductMapping;
import com.product.domain.Product;

@Service
public interface CategoryProductMappingService {
	
	void addCategoryProductMappingService(List<CategoryProductMapping> categoryProductMappings) throws Exception;
	
	List<Product> getAllProductsByCategoryId(Integer categoryId) throws Exception;

}
