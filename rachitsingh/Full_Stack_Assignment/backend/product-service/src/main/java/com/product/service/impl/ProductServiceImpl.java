package com.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.dao.impl.ProductDAOImpl;
import com.product.domain.Product;
import com.product.domain.ProductRequest;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	ProductDAOImpl productDAOImpl;
	
	@Override
	public void insertProduct(ProductRequest productRequest) {
		if (productRequest != null) {
			productDAOImpl.insertProduct(productRequest);
		}
		
	}
	
	@Override
	public List<Product> getProducts(String productName, Integer flagName){
		if (productName != null || productName.length() != 0) {
			flagName = 1;
		}
		else {
			flagName = 0;
		}
		return productDAOImpl.getProducts(productName,flagName);
	}

}
