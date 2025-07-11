package com.product.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.category.domain.CategoryProductMapping;
import com.product.domain.Product;

@Service
public interface CategoryProductMappingService {
	
	void addCategoryProductMapping(CategoryProductMapping categoryProductMapping);
	
	List<Product> getAllProductsByCategoryId(Integer categoryId);

}
