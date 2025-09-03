package com.sms.Dao.Interfaces;

import java.util.List;

import com.sms.models.Product;
import com.sms.models.ProductStock;

public interface ProductDao {
	
	 public String addProduct(Product product);
	 
	 
	 public List<Product> getProducts(String searchKey);
	 public List<ProductStock> ViewProductStock();
	  
}
