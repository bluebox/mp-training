package com.spring.InventryManagementSystem.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.InventryManagementSystem.domain.Item;

public class ItemRowMapper implements RowMapper<Item>{

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item =  new Item();
		item.setItem(rs.getString("item"));
		item.setItemCost(rs.getFloat("itemCost"));
		item.setItemId(rs.getInt("itemId"));
		item.setItemQuantity(rs.getInt("itemQuantity"));
		item.setOrderId(rs.getInt("orderId"));
		return item;
	}

}
