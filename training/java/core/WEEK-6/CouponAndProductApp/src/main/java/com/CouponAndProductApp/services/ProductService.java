package com.CouponAndProductApp.services;

import java.util.List;

import com.CouponAndProductApp.domain.Product;

public interface ProductService {
	boolean validateCreateProduct(Product product);

	List<Product> validateGetAllProducts();
}
