package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.domain.Items;

public interface ItemsDao {
	int addItem(Items item);

	boolean existsByName(String name);

	Items getItemById(int id);

	int updateItem(Items item);

	int changeAvailability(int id, ItemAvailability availability);

	int deleteItem(int id);

	Items getItemByName(String name);

	List<Items> getAllItems();

	List<Items> getSearchItems(String name, String description);

	void inserLog(Items item);

}
