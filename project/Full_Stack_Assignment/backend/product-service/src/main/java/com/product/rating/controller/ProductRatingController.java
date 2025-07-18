package com.product.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.rating.domain.ProductRating;
import com.product.rating.service.ProductRatingService;

@RestController
@RequestMapping("/ratings")
public class ProductRatingController {

    @Autowired
    private ProductRatingService ratingService;

    @PostMapping("/add")
    public String addRating(@RequestBody ProductRating rating) {
        ratingService.addRating(rating);
        return "Rating added successfully";
    }

    @GetMapping("/product")
    public List<ProductRating> getRatingsForProduct(@RequestParam (required=false) Long productId) {
        return ratingService.getRatingsForProduct(productId);
    }

    @GetMapping("/average")
    public List<Double> getAverageRating(@RequestParam (required = false) Long productId) {
        return ratingService.getAverageRating(productId);
    }

    @PutMapping("/update")
    public String updateRating(@RequestBody ProductRating rating) {
        ratingService.updateRating(rating);
        return "Rating updated successfully";
    }

    @GetMapping("/delete")
    public String deleteRating(@RequestParam (required = false) Long ratingId) {
        ratingService.deleteRating(ratingId);
        return "Rating deleted successfully";
    }
}

