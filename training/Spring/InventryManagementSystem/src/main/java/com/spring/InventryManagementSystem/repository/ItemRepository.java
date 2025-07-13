package com.spring.InventryManagementSystem.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.InventryManagementSystem.domain.Item;
import com.spring.InventryManagementSystem.interfaces.repository.ItemRepositoryInterface;

@Repository
public class ItemRepository implements ItemRepositoryInterface {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ItemRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void insertItems(Item item) {
		String sql = "INSERT INTO Item(orderId,item,itemQuantity,itemCost,status) VALUES (:orderId,:item,:itemQuantity,:itemCost,'ACTIVE')";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orderId", item.getOrderId());
		params.addValue("item", item.getItem());
		params.addValue("itemQuantity", item.getItemQuantity());
		params.addValue("itemCost", item.getItemCost());
		namedParameterJdbcTemplate.update(sql, params);
	}

}
