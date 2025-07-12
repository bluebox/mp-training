package com.orders.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrderCriteria {
	@NonNull
	private Long customerId;
	private List<String> orderStatuses;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private List<Long> orderIds;
	private Integer pageNumber;
	private Integer pageSize;
}
