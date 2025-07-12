package com.product.category.domain;

import com.product.enums.RequestStatus;

import lombok.Data;

@Data
public class CategoryRequestSearchCriteria extends CategorySearchCriteria {
	
	private RequestStatus status;
	
	private Long requestId;
}
