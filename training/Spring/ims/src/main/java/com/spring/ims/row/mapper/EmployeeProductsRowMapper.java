package com.spring.ims.row.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.ims.domain.EmployeeProducts;

public class EmployeeProductsRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeProducts product = new EmployeeProducts();
		product.setMaxQuantity(rs.getInt("maxQuan"));
		product.setMinQuantity(rs.getInt("minQuan"));
		product.setProductId(rs.getInt("procuctId"));
		product.setProductName(rs.getString("procuctName"));
		product.setSupplier(rs.getString("supplier"));
		product.setStockId(rs.getInt("stockId"));
		product.setQuantity(rs.getInt("quantity"));
		return product;
	}

}
