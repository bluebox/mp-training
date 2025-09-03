package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;
import com.rms.restaurant_management_system_backend.domain.Orders;

public class OrdersRowMapper implements RowMapper<Orders> {

	@Override
	public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
		Orders order = new Orders();
		order.setOrderId(rs.getInt("ord_id"));
		order.setCustomerId(rs.getInt("cust_id"));
		order.setWaiterId(rs.getInt("wtr_id"));
		order.setOrderDate(rs.getDate("ord_date").toLocalDate());
		order.setAmount(rs.getDouble("amount"));
		order.setStatus(OrderStatus.getEnumConstant(rs.getString("status")));
		return order;
	}
}
