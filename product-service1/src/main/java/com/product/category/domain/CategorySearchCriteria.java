package com.product.category.domain;

import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
public class CategorySearchCriteria {
	
	private Long categoryIds;
	private String categoryNames;
}
