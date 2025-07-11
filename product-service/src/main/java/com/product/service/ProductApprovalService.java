package com.product.service;

import java.util.List;

import com.product.Exceptions.ProductRequestDatabaseOperationException;
import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;

public interface ProductApprovalService {
	public List<ProductRequest> getRequests(List<Long> productRequestId,List<RequestStatus> status);
    public void approveRequest(List<Long> productRequestId, RequestStatus status, Long managerId) throws ProductRequestDatabaseOperationException;
    public void rejectRequest(List<Long> productRequestId, RequestStatus status, Long managerId);
}
