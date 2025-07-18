package com.orders.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.orders.enums.OrderStatus;
import com.orders.item.domain.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private Long orderId;
	private Long userId;
	private String address;
	private BigDecimal totalAmount;
	private OrderStatus status;
	private LocalDateTime placedAtDate;
	private LocalDateTime updatedAtDate;
	private List<OrderItem> orderItems;
}
