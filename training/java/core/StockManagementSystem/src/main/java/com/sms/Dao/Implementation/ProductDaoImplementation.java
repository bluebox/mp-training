package com.sms.Dao.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sms.Dao.Interfaces.ProductDao;
import com.sms.models.Product;
import com.sms.models.ProductStock;

@Repository
@Transactional
public class ProductDaoImplementation implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String addProduct(Product product) {
        String sql = "INSERT INTO tbl_product (product_id, name, status) VALUES (?, ?, ?)";

        try {
            int rows = jdbcTemplate.update(sql,
                    product.getProductId(),
                    product.getName(),
                    product.getStatus());

            if (rows > 0) {
                return "Product inserted successfully.";
            } else {
                return "Failed to insert product.";
            }
        } catch (Exception e) {
        	
            throw new RuntimeException("Error inserting product: " + e.getMessage().substring(100,126), e);
        }
    }

    @Override
    public List<Product> getProducts(String searchKey) {
        StringBuilder sql = new StringBuilder(
                "SELECT product_id, name, status FROM tbl_product WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (searchKey != null && !searchKey.trim().isEmpty()) {
            String trimmedKey = searchKey.trim().toLowerCase();

            boolean nameMatch = trimmedKey.matches("^[a-zA-Z]+$");
            boolean idMatch = trimmedKey.matches("^[a-zA-Z0-9]+$");

            if (nameMatch || idMatch) {
                sql.append(" AND (");

                boolean added = false;

                if (nameMatch) {
                    sql.append("LOWER(name) LIKE ?");
                    params.add("%" + trimmedKey + "%");
                    added = true;
                }

                if (idMatch) {
                    if (added) sql.append(" OR ");
                    sql.append("LOWER(product_id) LIKE ?");
                    params.add("%" + trimmedKey + "%");
                }

                sql.append(")");
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> {
            return Product.builder()
                    .productId(rs.getString("product_id"))
                    .name(rs.getString("name"))
                    .status(rs.getString("status"))
                    .build();
        });
    }

   
	@Override
	public List<ProductStock> ViewProductStock() {
		 String sql = "SELECT product_id, name, total_qty FROM tbl_products_stock";
	       
	        return jdbcTemplate.query(sql, (rs, rowNum) -> {
	            return new ProductStock(
	                rs.getString("product_id"),
	                rs.getString("name"),
	                rs.getLong("total_qty")
	            );
	        });
	}

	
}
