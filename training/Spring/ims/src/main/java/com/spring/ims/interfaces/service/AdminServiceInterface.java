package com.spring.ims.interfaces.service;

import java.util.List;

import com.spring.ims.domain.FullOrder;
import com.spring.ims.domain.Orders;

public interface AdminServiceInterface {

    boolean updateStatus(Orders order);

    List<Orders> pendingOrders();

    List<Orders> allOrders();

    FullOrder productsOfOrder(Orders order);

}

