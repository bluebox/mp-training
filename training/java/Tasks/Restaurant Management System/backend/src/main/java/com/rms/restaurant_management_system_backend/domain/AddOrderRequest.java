package com.rms.restaurant_management_system_backend.domain;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
	
	@NotBlank(message = "Name must not be blank")
	@Size(min = 3, max = 60, message = "Name must be between 3 and 60 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Customer name must contain only letters and spaces")
	String name;
	
	@NotBlank(message = "Mobile number must not be blank")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must be 10 digits and start with 6-9")
	String phone;
	
	@NotNull(message="waiter id must not be null")
	@Positive(message = "Waiter Id must be a positive number")
	int waiterId;
	
	@NotNull(message = "totalPrice must not be null")
	@Positive(message = "total Price must be a positive number")
	private int totalPrice;
	
	@NotNull(message="Ordered Items list must not be null")
	private List<OrderDetails> orderDetailsList;
}
