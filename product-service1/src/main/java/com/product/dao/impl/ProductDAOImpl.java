package com.product.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.product.dao.ProductDAO;
import com.product.domain.Product;
import com.product.domain.ProductRequest;

public class ProductDAOImpl implements ProductDAO{
	private JdbcTemplate jdbcTemplate;
	
	private static class ProductMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();

			product.setProductId(rs.getLong("ProductId"));
			product.setProductName(rs.getString("ProductName"));
			product.setDescription(rs.getString("Description"));
			product.setPrice(rs.getBigDecimal("Price"));
			product.setQuantity(rs.getInt("Quantity"));
			product.setAverageRating(rs.getBigDecimal("AverageRating"));
			product.setCreatedAtDate(
					rs.getTimestamp("CreatedAtDate") != null ? rs.getTimestamp("CreatedAtDate").toLocalDateTime()
							: null);
			product.setUpdatedAtDate(
					rs.getTimestamp("UpdatedAtDate") != null ? rs.getTimestamp("UpdatedAtDate").toLocalDateTime()
							: null);
			return product;
		}
	}
	
	
	@Override
	public void insertProduct(ProductRequest productRequest) {
		String sqlQuery = "INSERT INTO products (ProductName, Description, Price, Quantity) "
				+ "VALUES (:name,:description,:price,:quantity)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", productRequest.getProductName());
		parameters.addValue("description",productRequest.getDescription());
		parameters.addValue("price", productRequest.getPrice());
		parameters.addValue("quantity", productRequest.getQuantity());
		jdbcTemplate.update(sqlQuery,parameters);
	}

	@Override
	public List<Product> getProducts(String productName,Integer flagName) {
		String sqlQuery = "SELECT ProductId, ProductName, Description, "+
							"Price, Quantity, AverageRating, "+
							"CreatedAtDate, UpdatedAtDate FROM products"+
							"WHERE ((0 = :flagName) OR (ProductName = :productName))";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("flagName", flagName);
		parameters.addValue("productName", productName);
		return jdbcTemplate.query(sqlQuery, new ProductMapper());
	}

}
