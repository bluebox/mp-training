package com.product.category.domain;

import java.time.LocalDateTime;

import com.product.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
	private Long categoryRequestId;
    private String categoryName;
    private RequestStatus status;
    private Long requestedBy;
    private Long approvedBy; 
    private LocalDateTime createdAtDate;
    private LocalDateTime updatedAtDate;

}
