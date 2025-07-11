package com.product.domain;

import java.util.List;

import com.product.enums.RequestStatus;

import lombok.Data;

@Data
public class SearchProductRequestCriteria {
	private List<Long> requestIds;
	private List<String> productNames;
	private RequestStatus status;
	private Long loggedInUserId;
}
