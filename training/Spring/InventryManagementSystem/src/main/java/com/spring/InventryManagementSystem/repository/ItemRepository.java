package com.spring.InventryManagementSystem.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.InventryManagementSystem.domain.Item;
import com.spring.InventryManagementSystem.interfaces.repository.ItemRepositoryInterface;
import com.spring.InventryManagementSystem.row.mappers.ItemRowMapper;

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

	@Override
	public void setStatusInactive(Integer orderId) {
		String sql = "Update Item set status='INACTIVE' where orderId=:orderId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orderId", orderId);
		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public List<Item> getAllItemsOfOrder(Integer orderId) {
		String sql = "SELECT * FROM Item WHERE orderId = :orderId AND status = 'ACTIVE'";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orderId", orderId);
		return namedParameterJdbcTemplate.query(sql, params,new ItemRowMapper());
	}

	@Override
	public List<Item> getAllItemsOfOrderWithoutStatus(Integer orderId) {
		String sql = "SELECT * FROM Item WHERE orderId = :orderId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orderId", orderId);
		return namedParameterJdbcTemplate.query(sql, params,new ItemRowMapper());
	}

	@Override
	public void updateItem(Item item) {
		String sql = "UPDATE Item SET orderId=:orderId,item=:item,itemQuantity=:itemQuantity,itemCost=:itemCost,status='ACTIVE' WHERE itemId=:itemId";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orderId", item.getOrderId());
		params.addValue("item", item.getItem());
		params.addValue("itemQuantity", item.getItemQuantity());
		params.addValue("itemCost", item.getItemCost());
		params.addValue("itemId", item.getItemId());
		namedParameterJdbcTemplate.update(sql, params);
	}
		
}

