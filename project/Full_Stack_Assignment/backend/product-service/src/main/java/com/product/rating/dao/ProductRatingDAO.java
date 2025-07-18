package com.product.rating.dao;

import java.util.List;

import com.product.rating.domain.ProductRating;

public interface ProductRatingDAO {
    void addRating(ProductRating rating);
    List<ProductRating> getRatingsForProduct(Long productId);
    List<Double> getAverageRatingForProduct(Long productId);
    void updateRating(ProductRating rating);
    void deleteRating(Long ratingId);
}
