package com.spring.ims.interfaces.service;

import java.util.List;

import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.OrderProductDetails;

public interface PurchaseCreationServiceInterface {

    List<String> listOfSuppliers();

    List<String> listOfProducts(List<String> searchCriteria);

    float costOfProduct(OrderProductDetails orderProductDetails);

    boolean creationOfOrder(FullOrder fullOrder);
}

