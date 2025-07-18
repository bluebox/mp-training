package com.orders.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orders.enums.OrderStatus;

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
	private List<OrderStatus> orderStatuses;
	private String address;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime fromDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime toDate;
	private List<Long> orderIds;
	private Integer pageNumber = 0;
	private Integer pageSize = 10;

	private int orderIdsFlag;

	private int orderStatusFlag;

	private int fromDateFlag;

	private int toDateFlag;

}
