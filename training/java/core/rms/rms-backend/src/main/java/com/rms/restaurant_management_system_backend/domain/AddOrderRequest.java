package com.rms.restaurant_management_system_backend.domain;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
	
	String name;
	
	@NotNull(message = "Customer Phone number must not be null")
	String phone;
	
	@NotNull(message="waiter id must ot be null")
	@Positive(message = "Waiter Id must be a positive number")
	int waiterId;
	
	@NotNull(message = "totalPrice must not be null")
	@Positive(message = "total Price must be a positive number")
	private int totalPrice;
	
	@NotNull(message="Ordered Items list must not be null")
	private List<OrderDetails> orderDetailsList;
}
