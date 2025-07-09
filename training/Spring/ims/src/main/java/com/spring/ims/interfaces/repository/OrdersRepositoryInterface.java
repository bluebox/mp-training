package com.spring.ims.interfaces.repository;

import java.util.List;

import com.spring.ims.domain.Orders;

public interface OrdersRepositoryInterface {

    int addOrder(Orders order);

    boolean approval(Orders order);

    List<Orders> pendingOrders();

    List<Orders> allOrders();

    boolean withDrawOfOrder(Orders order);

    boolean updateOrder(Orders order);

    boolean checkStatus(Orders order);

    List<Orders> adminAllOrders();
}

