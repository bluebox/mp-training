package com.spring.ims.interfaces.service;

import java.util.List;

import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.OrderProductDetails;
import com.spring.ims.domain.Orders;

public interface OrdersServiceInterface {

    List<Orders> pendingOrders();

    List<Orders> allOrders();

    FullOrder productsOfOrder(Orders order);

    float costOfProduct(OrderProductDetails orderProductDetails);

    boolean editingOfOrder(FullOrder fullOrder);

    boolean withDrawOfOrder(Orders order);
}

