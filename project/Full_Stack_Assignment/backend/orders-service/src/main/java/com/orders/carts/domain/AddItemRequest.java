package com.orders.carts.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private String productName;
}
