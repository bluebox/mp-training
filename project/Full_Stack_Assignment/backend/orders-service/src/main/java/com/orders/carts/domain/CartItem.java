package com.orders.carts.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long cartItemId;
    private Long cartId;
    private Long userId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;   
    private LocalDateTime addedAtDate;
}
