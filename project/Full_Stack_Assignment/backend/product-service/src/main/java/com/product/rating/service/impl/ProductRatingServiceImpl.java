package com.product.rating.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.rating.dao.ProductRatingDAO;
import com.product.rating.domain.ProductRating;
import com.product.rating.service.ProductRatingService;

@Service
public class ProductRatingServiceImpl implements ProductRatingService {

    @Autowired
    private ProductRatingDAO ratingDAO;

    @Override
    public void addRating(ProductRating rating) {
        ratingDAO.addRating(rating);
    }

    @Override
    public List<ProductRating> getRatingsForProduct(Long productId) {
        return ratingDAO.getRatingsForProduct(productId);
    }

    @Override
    public List<Double> getAverageRating(Long productId) {
        return ratingDAO.getAverageRatingForProduct(productId);
    }

    @Override
    public void updateRating(ProductRating rating) {
        ratingDAO.updateRating(rating);
    }

    @Override
    public void deleteRating(Long ratingId) {
        ratingDAO.deleteRating(ratingId);
    }
}

