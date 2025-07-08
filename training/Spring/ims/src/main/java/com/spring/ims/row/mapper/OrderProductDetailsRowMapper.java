package com.spring.ims.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.ims.domain.OrderProductDetails;

public class OrderProductDetailsRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderProductDetails orderProductDetails = new OrderProductDetails();
		orderProductDetails.setOrderDetailsId(rs.getInt("orderDetailsId"));
		orderProductDetails.setOrderId(rs.getInt("orderId"));
		orderProductDetails.setProduct(rs.getString("product"));
		orderProductDetails.setProductCost(rs.getFloat("productCost"));
		orderProductDetails.setProductQuantity(rs.getInt("productQuantity"));
		orderProductDetails.setSupplier(rs.getString("supplier"));
		return orderProductDetails;
	}

}
