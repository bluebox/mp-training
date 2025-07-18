package com.product.category.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestSearchCriteria extends CategorySearchCriteria {
	

	private String categoryName;
	
	private String status;
	
	private Long requestId;
}
