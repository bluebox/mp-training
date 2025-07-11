package com.product.category.domain;

import java.time.LocalDateTime;

import com.product.enums.RequestStatus;

import lombok.Data;


@Data
public class CategoryRequest {
	private Integer categoryRequestId;
    private String categoryName;
    private RequestStatus status;
    private Long requestedBy;
    private Long approvedBy; 
    private LocalDateTime createdAtDate;
    private LocalDateTime updatedAtDate;

}
