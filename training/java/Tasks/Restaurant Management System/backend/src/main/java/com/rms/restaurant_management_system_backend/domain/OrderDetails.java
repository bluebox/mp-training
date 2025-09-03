package com.rms.restaurant_management_system_backend.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

	private int orderDetailsId;

//	@NotNull(message = "Order id cannot be null")
	private int orderId;

	@NotNull(message = "Item id cannot be null")
	private int itemId;

	@NotNull(message = "Item quantity cannot be null")
	@Min(value = 1, message = "Quantity must atleast one")
	private int quantity;

	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "Price must be greater than or equal to 1")
	private double price;
}