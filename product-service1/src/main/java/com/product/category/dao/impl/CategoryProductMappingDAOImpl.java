package com.product.category.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.product.category.dao.CategoryProductMappingDAO;
import com.product.category.domain.CategoryProductMapping;
import com.product.domain.Product;

public class CategoryProductMappingDAOImpl implements CategoryProductMappingDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CategoryProductMappingDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, rowNum) -> {
		Product product = new Product();
		product.setProductId(rs.getLong("ProductId"));
		product.setProductName(rs.getString("Name"));
		product.setDescription(rs.getString("Description"));
		product.setPrice(rs.getBigDecimal("Price"));

		Timestamp createdAt = rs.getTimestamp("CreatedAt");
		Timestamp updatedAt = rs.getTimestamp("UpdatedAt");

		return product;
	};

	@Override
	public void createMapping(CategoryProductMapping categoryProductMapping) {
		// TODO Auto-generated method stub
		String insertMapping = "INSERT INTO category_product_mapping (ProductId, CategoryId, CreatedAtDate, UpdatedAtDate) "
				+ "VALUES (?, ?, ?, ?)";

		try {
			jdbcTemplate.update(insertMapping, categoryProductMapping.getProductId(),
					categoryProductMapping.getCategoryId(),
					Timestamp.valueOf(categoryProductMapping.getCreatedAtDate()),
					categoryProductMapping.getUpdatedAtDate() != null
							? Timestamp.valueOf(categoryProductMapping.getUpdatedAtDate())
							: null);
		} catch (Exception e) {
			throw new Error("Error adding category Product Mapping", e);
		}
	}

	@Override
	public List<Product> findAllProductsByCategoryId(Integer categoryId) {
		String fetchrecords = "SELECT p.* FROM products p "
				+ "JOIN category_product_mapping cpm ON p.ProductId = cpm.ProductId " + "WHERE cpm.CategoryId = ?";
		try {
			return jdbcTemplate.query(fetchrecords, new Object[] { categoryId }, PRODUCT_ROW_MAPPER);
		} catch (Exception e) {
			throw new Error("Error fetching category Products through Mapping id", e);
		}
	}

}
