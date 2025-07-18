package com.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dao.ProductDAO;
import com.product.domain.Product;
import com.product.domain.ProductRequest;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public void insertProduct(List<ProductRequest> productRequest) {
		if (productRequest != null && productRequest.size() != 0) {
			productDAO.insertProduct(productRequest);
		}
		
	}
	
	@Override
	public List<Product> getProducts(String productName){
		Integer flagName = 0;
		if (productName != null && !productName.trim().isEmpty()) {
			flagName = 1;
		}
		else {
			flagName = 0;
		}
		return productDAO.getProducts(productName,flagName);
	}

}