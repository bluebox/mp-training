package com.product.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.category.dao.CategoryProductMappingDAO;
import com.product.category.domain.CategoryProductMapping;
import com.product.category.service.CategoryProductMappingService;
import com.product.domain.Product;

@Service
public class CategoryProductMappingServiceImpl implements CategoryProductMappingService {

	private final CategoryProductMappingDAO categoryProductMappingDAO;

	@Autowired
	public CategoryProductMappingServiceImpl(CategoryProductMappingDAO categoryProductMappingDAO) {
		this.categoryProductMappingDAO = categoryProductMappingDAO;
	}

	@Override
	public void addCategoryProductMappingService(List<CategoryProductMapping> categoryProductMappings) throws Exception {
		if (categoryProductMappings == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}

		categoryProductMappingDAO.createMapping(categoryProductMappings);

	}

	@Override
	public List<Product> getAllProductsByCategoryId(Integer categoryId) throws Exception {
		if (categoryId == null || categoryId <= 0) {
			throw new IllegalArgumentException("categoryId must not be null or negative");
		}

		return categoryProductMappingDAO.findAllProductsByCategoryId(categoryId);
	}

}
