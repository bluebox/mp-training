package com.orders.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	private Long orderItemId;
	private Long orderId;
	private Long productId;
	private int quantity;
	private BigDecimal price;
	private LocalDateTime createdAt;
}
