package com.spring.ims.interfaces.repository;

import java.util.List;

import com.spring.ims.domain.OrderProductDetails;
import com.spring.ims.domain.Orders;

public interface OrderProductDetailsRepositoryInterface {

    boolean addProducts(List<OrderProductDetails> listOfProducts, int orderId);

    List<OrderProductDetails> ProductsOfOrder(Orders order);

    boolean editProducts(List<OrderProductDetails> listOfProducts, int orderId);

    boolean makingInactive(int orderId);
}
