package com.sms.service.Interfaces;

import java.util.List;

import com.sms.models.Product;
import com.sms.models.ProductStock;

public interface ProductService {
	
	
 public String addProduct(Product product);
 
 public List<Product> getProducts(String searchKey);

 public List<ProductStock> ViewProductStock();
 
}
