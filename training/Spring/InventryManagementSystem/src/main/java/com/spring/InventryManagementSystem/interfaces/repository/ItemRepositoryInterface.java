package com.spring.InventryManagementSystem.interfaces.repository;

import java.util.List;

import com.spring.InventryManagementSystem.domain.Item;

public interface ItemRepositoryInterface {

	void insertItems(Item item);

	void setStatusInactive(Integer orderId) ;

	List<Item> getAllItemsOfOrder(Integer orderId);

	List<Item> getAllItemsOfOrderWithoutStatus(Integer orderId);

	void updateItem(Item item);

}
