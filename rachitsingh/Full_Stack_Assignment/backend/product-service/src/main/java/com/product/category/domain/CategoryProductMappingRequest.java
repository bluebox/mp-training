package com.product.category.domain;

import java.time.LocalDateTime;

import com.product.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryProductMappingRequest {
	private Long mappingRequestId;
    private Long productId;
    private Integer categoryId;
    private Long requestedBy;
    private RequestStatus status;
    private Long approvedBy;
    private LocalDateTime createdAtDate;
    private LocalDateTime updatedAtDate;
}
