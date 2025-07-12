package com.product.category.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.product.category.dao.CategoryProductMappingDAO;
import com.product.category.domain.CategoryProductMapping;
import com.product.domain.Product;

@Repository
@Transactional
public class CategoryProductMappingDAOImpl implements CategoryProductMappingDAO {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public CategoryProductMappingDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, rowNum) -> {
		Product product = new Product();
		product.setProductId(rs.getLong("ProductId"));
		product.setProductName(rs.getString("ProductName"));
		product.setDescription(rs.getString("Description"));
		product.setPrice(rs.getBigDecimal("Price"));
		product.setQuantity(rs.getInt("Quantity"));
	    product.setAverageRating(rs.getBigDecimal("AverageRating"));
		return product;
	};

	@Override
	public void createMapping(List<CategoryProductMapping> categoryProductMappings) throws Exception {
		String insertMapping = "INSERT INTO category_product_mapping (ProductId, CategoryId, CreatedAtDate, UpdatedAtDate) " +
				"VALUES (:productId, :categoryId, :createdAt, :updatedAt)";

		List<Map<String, Object>> batchValues = new ArrayList<>(categoryProductMappings.size());
		for (CategoryProductMapping categoryProductMapping : categoryProductMappings) {
			Map<String, Object> params = new HashMap<>();
			params.put("productId", categoryProductMapping.getProductId());
			params.put("categoryId", categoryProductMapping.getCategoryId());
			params.put("createdAt", Timestamp.valueOf(categoryProductMapping.getCreatedAtDate()));
			params.put("updatedAt", categoryProductMapping.getUpdatedAtDate() != null
					? Timestamp.valueOf(categoryProductMapping.getUpdatedAtDate())
					: null);
			batchValues.add(params);
		}

		try {
			Map<String, Object>[] paramArray = new HashMap[batchValues.size()];
			namedParameterJdbcTemplate.batchUpdate(insertMapping, batchValues.toArray(paramArray));
		} catch (Exception e) {
			throw new Exception("Error adding category Product Mappings in batch", e);
		}
	}

	@Override
	public List<Product> findAllProductsByCategoryId(Integer categoryId) throws Exception {
		String fetchRecords = "SELECT p.* FROM products p " +
				"JOIN category_product_mapping cpm ON p.ProductId = cpm.ProductId " +
				"WHERE cpm.CategoryId = :categoryId";

		Map<String, Object> params = new HashMap<>();
		params.put("categoryId", categoryId);

		try {
			return namedParameterJdbcTemplate.query(fetchRecords, params, PRODUCT_ROW_MAPPER);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error fetching category Products through Mapping ID", e);
		}
	}
}
