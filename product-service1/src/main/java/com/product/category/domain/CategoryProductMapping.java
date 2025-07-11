package com.product.category.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryProductMapping {

	private Long productId;
    private Integer categoryId;
    private LocalDateTime createdAtDate;
    private LocalDateTime updatedAtDate;
}
