package com.product.domain;

import java.util.List;

import com.product.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalActionRequest {
	private List<Long> productRequestIds;
	private RequestStatus status;
	private Long managerId;
}