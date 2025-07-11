package com.spring.ims.interfaces.repository;

import java.util.List;

public interface ProductRepositoryInterface {

    List<String> listOfProducts(int supplierId, String search);

    float costOfProduct(int productId);

    int supplierOfProduct(int productId);
    
    float fetchProductCost(int productId);
}

