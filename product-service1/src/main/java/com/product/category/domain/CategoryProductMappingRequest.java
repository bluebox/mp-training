package com.product.category.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryProductMappingRequest {
	private Integer mappingRequestId;
    private Long productId;
    private Integer categoryId;
    private Long requestedBy;
    private Character status;
    private Long approvedBy;
    private LocalDateTime createdAtDate;
    private LocalDateTime updatedAtDate;
}
