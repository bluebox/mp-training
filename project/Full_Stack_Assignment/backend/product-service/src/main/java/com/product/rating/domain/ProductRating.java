package com.product.rating.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRating {
    private Long ratingId;
    private Long productId;
    private Long userId;
    private Integer rating;
    private LocalDateTime createdAt;

   
}
