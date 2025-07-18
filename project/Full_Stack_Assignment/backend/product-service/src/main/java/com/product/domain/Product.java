package com.product.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal averageRating = BigDecimal.valueOf(0.0);
    private LocalDateTime createdAtDate;
    private LocalDateTime updatedAtDate;
}
