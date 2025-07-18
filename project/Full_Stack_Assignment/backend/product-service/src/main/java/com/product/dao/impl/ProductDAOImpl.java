package com.product.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.product.dao.ProductDAO;
import com.product.domain.Product;
import com.product.domain.ProductRequest;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
            product.setCreatedAtDate(rs.getTimestamp("CreatedAtDate") != null
                    ? rs.getTimestamp("CreatedAtDate").toLocalDateTime()
                    : null);
            product.setUpdatedAtDate(rs.getTimestamp("UpdatedAtDate") != null
                    ? rs.getTimestamp("UpdatedAtDate").toLocalDateTime()
                    : null);

            return product;
        }
    }

    @Override
    public void insertProduct(List<ProductRequest> productRequests) {
        if (productRequests == null || productRequests.isEmpty()) {
            throw new IllegalArgumentException("Product request list is empty or null.");
        }

        String sqlQuery = "INSERT INTO products (ProductName, Description, Price, Quantity) "
                + "VALUES (:productName, :description, :price, :quantity)";

        List<MapSqlParameterSource> batchParameters = productRequests.stream()
                .map(p -> new MapSqlParameterSource()
                        .addValue("productName", p.getProductName())
                        .addValue("description", p.getDescription())
                        .addValue("price", p.getPrice())
                        .addValue("quantity", p.getQuantity()))
                .collect(Collectors.toList());

        try {
            int[] results = jdbcTemplate.batchUpdate(sqlQuery,
                    batchParameters.toArray(new MapSqlParameterSource[0]));

            for (int i = 0; i < results.length; i++) {
                if (results[i] == 0) {
                    System.err.println("Insert failed for product: " + productRequests.get(i));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while inserting products into DB: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getProducts(String productName, Integer flagName) {
        String sqlQuery = "SELECT ProductId, ProductName, Description, Price, Quantity, AverageRating, "
                + "CreatedAtDate, UpdatedAtDate FROM products "
                + "WHERE ((0 = :flagName) OR (ProductName = :productName))";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("flagName", flagName);
        parameters.addValue("productName", productName);

        return jdbcTemplate.query(sqlQuery, parameters, new ProductMapper());
    }
}
