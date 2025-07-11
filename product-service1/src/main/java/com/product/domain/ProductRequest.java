package com.product.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.product.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long productRequestId;
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Long requestedBy;
    private RequestStatus status;
    private Long approvedBy;
    private LocalDateTime createdAtDate;
    private LocalDateTime updatedAtDate;
}
