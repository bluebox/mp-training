package com.rms.restaurant_management_system_backend.custom_classes;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class OrdersSearch {
	private Integer orderId;
	private Integer customerId;
	private Integer waiterId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double amount;
	private List<String> statuses;
}
