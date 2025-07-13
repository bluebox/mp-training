package com.spring.InventryManagementSystem.row.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import org.springframework.jdbc.core.RowMapper;

import com.spring.InventryManagementSystem.domain.OrderDetails;

public class OrderDetailsRowMapper implements RowMapper<OrderDetails>{

	@Override
	public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderCost(rs.getFloat("orderCost"));
		orderDetails.setItem(Collections.EMPTY_LIST);
		orderDetails.setOrderDate(rs.getDate("orderDate"));
		orderDetails.setOrderDiscount(rs.getInt("orderDiscount"));
		orderDetails.setOrderSupplier(rs.getString("orderSupplier"));
		orderDetails.setOrderStatus(rs.getString("orderStatus"));
		orderDetails.setOrderId(rs.getInt("orderId"));
		return orderDetails;
	}

}
