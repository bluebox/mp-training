package com.product.rating.service;

import java.util.List;

import com.product.rating.domain.ProductRating;

public interface ProductRatingService {
    void addRating(ProductRating rating);
    List<ProductRating> getRatingsForProduct(Long productId);
    List<Double> getAverageRating(Long productId);
    void updateRating(ProductRating rating);
    void deleteRating(Long ratingId);
}
