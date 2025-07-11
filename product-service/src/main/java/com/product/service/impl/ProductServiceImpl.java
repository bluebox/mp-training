package com.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dao.ProductDAO;
import com.product.domain.Product;
import com.product.domain.ProductRequest;
import com.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductDAO productDAO;
	
	@Override
	public void insertProduct(List<ProductRequest> productRequest) {
		if (productRequest != null) {
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
