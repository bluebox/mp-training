package com.product.category.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Category {
	
	private Integer categoryId;
	private String categoryName;
	private LocalDateTime createdAtDate;
	private LocalDateTime updatedAtDate;

}
