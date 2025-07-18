package com.product.dto;

import java.util.List;

import com.product.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMappingRequestStatusRequest {
    private List<Long> requestIds;
    private Long approvedBy;
    private RequestStatus status;

}

