package com.rms.restaurant_management_system_backend.domain;

import java.time.LocalDate;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

	private int orderId;

//	@NotNull(message = "Customer id is required")
	private int customerId;

//	@NotNull(message = "Staff id is required")
	private int waiterId;

//	@NotBlank(message = "Order Date is required")
	private LocalDate orderDate;

//	@NotNull(message = "Amount is required")
//	@Positive(message = "Amount must be a positive number")
	private double amount;

//	@NotBlank(message = "Status is required")
	private OrderStatus status;

	public Orders(int customerId, int waiterId, double totalPrice) {
		this.customerId = customerId;
		this.waiterId = waiterId;
		this.amount=totalPrice;
	}
}
