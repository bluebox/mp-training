package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rms.restaurant_management_system_backend.domain.OrderDetails;

public class OrderDetailsRowMapper implements RowMapper<OrderDetails> {
	
	@Override
	public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderDetailsId(rs.getInt("ord_details_id"));
		orderDetails.setOrderId(rs.getInt("ord_id"));
		orderDetails.setItemId(rs.getInt("item_id"));
		orderDetails.setQuantity(rs.getInt("quantity"));
		orderDetails.setPrice(rs.getDouble("price"));
		
		return orderDetails;
	}
}
