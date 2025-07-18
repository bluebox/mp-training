package com.product.dao;

import java.util.List;

import com.product.domain.Product;
import com.product.domain.ProductRequest;

public interface ProductDAO {
	public void insertProduct(List<ProductRequest> productRequest);
	public List<Product> getProducts(String productName,Integer flagName);
}