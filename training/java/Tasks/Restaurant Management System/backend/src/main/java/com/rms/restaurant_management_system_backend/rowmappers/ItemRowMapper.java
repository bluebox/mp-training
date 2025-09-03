package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemCategory;
import com.rms.restaurant_management_system_backend.constant.ItemStatus;
import com.rms.restaurant_management_system_backend.domain.Items;

@Component
public class ItemRowMapper implements RowMapper<Items> {
	
	@Override
	public Items mapRow(ResultSet rs, int rowNum) throws SQLException {
		Items item = new Items();

		item.setId(rs.getInt("item_id"));
		item.setName(rs.getString("name"));
		item.setImageUrl(rs.getString("image"));
		item.setPrice(rs.getDouble("price"));
		item.setDescription(rs.getString("description"));
		item.setCategory(ItemCategory.fromName(rs.getString("category")));
		item.setAvailable(ItemAvailability.fromName(rs.getString("availability")));
		item.setStatus(ItemStatus.fromName(rs.getString("status")));

		return item;
	}

}
