package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.dao.ItemsDao;
import com.rms.restaurant_management_system_backend.domain.Items;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

	private final ItemsDao itemsDao;

	public ItemsServiceImpl(ItemsDao itemsDao) {
		this.itemsDao = itemsDao;
	}

	@Override
	public Items saveItem(Items item) {

		if (item == null) {
			throw new RestaurantOperationException("Items details are wrong");
		}

		if (itemsDao.existsByName(item.getName())) {
			throw new RestaurantOperationException("Item already exists.");
		}

		int itemSaved = itemsDao.addItem(item);

		if (itemSaved <= 0) {
			throw new RestaurantOperationException("Item Not Added Please try again");
		}
		return itemsDao.getItemByName(item.getName());
	}

	@Override
	public Items getItemById(int id) {

		return itemsDao.getItemById(id);
	}

	@Override
	@Transactional
	public Items updateItem(int id, Items updatedItem) {

		Items existingItem = itemsDao.getItemById(id);
		if (updatedItem == null || existingItem == null) {
			throw new RestaurantOperationException("Item not found");
		}

		if (!(updatedItem.getName().equals(existingItem.getName())) && itemsDao.existsByName(updatedItem.getName())) {
			System.out.println(updatedItem + "" + existingItem);
			throw new RestaurantOperationException("Item already exists.");
		}

		if (updatedItem.equals(existingItem)) {
			throw new RestaurantOperationException("Please edit at least one field");
		}

		itemsDao.inserLog(existingItem);

		existingItem.setName(updatedItem.getName());
		existingItem.setDescription(updatedItem.getDescription());
		existingItem.setPrice(updatedItem.getPrice());
		existingItem.setCategory(updatedItem.getCategory());
		existingItem.setImageUrl(updatedItem.getImageUrl());

		int updated = itemsDao.updateItem(existingItem);
		if (updated <= 0) {
			throw new RestaurantOperationException("Item Not Updated, Please try again");
		}
		return existingItem;
	}

	@Override
	@Transactional
	public Items changeAvailability(int id) {

		Items existingItem = itemsDao.getItemById(id);
		if (existingItem == null) {
			throw new RestaurantOperationException("Item not found");
		}

		itemsDao.inserLog(existingItem);

		if (existingItem.getAvailable().equals(ItemAvailability.AVAILABLE)) {
			existingItem.setAvailable(ItemAvailability.UNAVAILABLE);
		} else {
			existingItem.setAvailable(ItemAvailability.AVAILABLE);
		}

		int updated = itemsDao.changeAvailability(id, existingItem.getAvailable());

		if (updated <= 0) {
			throw new RestaurantOperationException("Availability Not Updated, Please try again");
		}

		return existingItem;
	}

	@Override
	public List<Items> getAllItems() {

		return itemsDao.getAllItems();
	}

	@Override
	public List<Items> getSearchItems(String search, String category) {
		return itemsDao.getSearchItems(search, category);
	}

	@Override
	@Transactional
	public Items deleteItem(int id) {

		Items existingItem = itemsDao.getItemById(id);

		if (existingItem == null) {
			throw new RestaurantOperationException("Item not found");
		}

		itemsDao.inserLog(existingItem);

		int rowEffected = itemsDao.deleteItem(id);

		if (rowEffected <= 0) {
			throw new RestaurantOperationException("Item Not Delected, Please try again");
		}
		return existingItem;
	}

}
