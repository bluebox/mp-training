package com.product.category.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	private Integer categoryId;
	private String categoryName;
	private LocalDateTime createdAtDate;
	private LocalDateTime updatedAtDate;

}
