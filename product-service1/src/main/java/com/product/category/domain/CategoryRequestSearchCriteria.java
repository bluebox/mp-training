package com.product.category.domain;

import lombok.Data;

@Data
public class CategoryRequestSearchCriteria extends CategorySearchCriteria {
	
	private Long requestId;
}
