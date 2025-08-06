package com.CouponAndProductApp.dao;

import java.util.List;

import com.CouponAndProductApp.domain.Product;

public interface ProductDao {
	boolean createProduct(Product product);

	List<Product> getAllProducts();
}
