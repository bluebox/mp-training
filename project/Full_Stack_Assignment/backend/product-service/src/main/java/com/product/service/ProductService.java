package com.product.service;

import java.util.List;

import com.product.domain.Product;
import com.product.domain.ProductRequest;

public interface ProductService {
	public void insertProduct(List<ProductRequest> productRequest);
	public List<Product> getProducts(String productName);
}