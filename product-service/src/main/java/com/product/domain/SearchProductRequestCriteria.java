package com.product.domain;

import java.util.List;

import com.product.enums.RequestStatus;

import lombok.Data;

@Data
public class SearchProductRequestCriteria {
	private Boolean showAll;
	private List<Long> requestIds;
	private List<String> productNames;
	private RequestStatus status;
	private Long loggedInUserId;
	private Integer pageNumber = 0;
	private Integer pageSize = 10;
}
