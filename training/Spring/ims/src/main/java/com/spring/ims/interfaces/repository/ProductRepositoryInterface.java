package com.spring.ims.interfaces.repository;

import java.util.List;

import com.spring.ims.domain.Product;

public interface ProductRepositoryInterface {

    List<String> listOfProducts(int supplierId, String search);

    float costOfProduct(int productId);

    int supplierOfProduct(int productId);
    
    Product fetchProduct(String productName,int productId);
}

