package com.spring.ims.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.ims.domain.Orders;

public class OrdersRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Orders order = new Orders();
		order.setOrderId(rs.getInt("orderId"));
		order.setOrderCost(rs.getFloat("orderCost"));
		order.setOrderDate(rs.getDate("orderDate"));
		order.setOrderDiscount(rs.getInt("orderDiscount"));
		order.setOrderStatus(rs.getString("orderStatus"));
		return order;
	}

}
