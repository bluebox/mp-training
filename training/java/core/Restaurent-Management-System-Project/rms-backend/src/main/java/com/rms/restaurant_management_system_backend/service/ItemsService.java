package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Items;

public interface ItemsService {

	Items saveItem(Items item);

	Items getItemById(int id);

	Items updateItem(int id, Items item);

	Items changeAvailability(int id);

	Items deleteItem(int id);

	List<Items> getAllItems();

	List<Items> getSearchItems(String name, String category);

}
