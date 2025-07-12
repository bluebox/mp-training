package com.product.service;

import java.util.List;

import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;

public interface ProductApprovalService {
	public List<ProductRequest> getRequests(List<Long> productRequestId,List<RequestStatus> status);
    public void updateRequest(List<Long> productRequestId, RequestStatus status, Long managerId);
}
