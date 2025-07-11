package com.product.category.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.category.domain.CategoryProductMapping;
import com.product.domain.Product;

@Service
public interface CategoryProductMappingDAO {

	void createMapping(List<CategoryProductMapping> categoryProductMapping) throws Exception;

	List<Product> findAllProductsByCategoryId(Integer categoryId) throws Exception;
	
}
