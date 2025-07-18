package com.product.rating.dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.product.rating.dao.ProductRatingDAO;
import com.product.rating.domain.ProductRating;

@Repository
public class ProductRatingDAOImpl implements ProductRatingDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<ProductRating> productRatingRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            ProductRating rating = new ProductRating();
            rating.setRatingId(rs.getLong("RatingId"));
            rating.setProductId(rs.getLong("ProductId"));
            rating.setUserId(rs.getLong("UserId"));
            rating.setRating(rs.getInt("Rating"));
            Timestamp ts = rs.getTimestamp("CreatedAt");
            rating.setCreatedAt(ts != null ? ts.toLocalDateTime() : null);
            return rating;
        };
    }

    @Override
    public void addRating(ProductRating rating) {
        String sql = "INSERT INTO product_ratings (ProductId, UserId, Rating, CreatedAt) " +
                     "VALUES (:productId, :userId, :rating, :createdAt)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", rating.getProductId());
        params.addValue("userId", rating.getUserId());
        params.addValue("rating", rating.getRating());
        params.addValue("createdAt", LocalDateTime.now());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<ProductRating> getRatingsForProduct(Long productId) {
    	
    	int productIdFlag = productId == null ? 0:1;
    	
        String sql = "SELECT * FROM product_ratings WHERE (0= :productidFlag or ProductId = :productId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productidFlag", productIdFlag);
        params.addValue("productId", productId);

        return namedParameterJdbcTemplate.query(sql, params, productRatingRowMapper());
    }

 
    @Override
    public List<Double> getAverageRatingForProduct(Long productId) {
        int productIdFlag = (productId == null) ? 0 : 1;

        String sql = "SELECT AVG(Rating) FROM product_ratings " +
                     "WHERE (:productIdFlag = 0 OR ProductId = :productId) " +
                     "GROUP BY ProductId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productIdFlag", productIdFlag);
        params.addValue("productId", productId);

        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getDouble(1));
    }


    @Override
    public void updateRating(ProductRating rating) {
        String sql = "UPDATE product_ratings SET Rating = :rating WHERE RatingId = :ratingId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ratingId", rating.getRatingId());
        params.addValue("rating", rating.getRating());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteRating(Long ratingId) {
        String sql = "DELETE FROM product_ratings WHERE RatingId = :ratingId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ratingId", ratingId);

        namedParameterJdbcTemplate.update(sql, params);
    }
}
