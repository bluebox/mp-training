package com.product.category.domain;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySearchCriteria {
	
	private Long categoryIds;
	private String categoryNames;
}
