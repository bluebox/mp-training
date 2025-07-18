package com.product.service;

import java.util.List;

import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;
import com.product.Exceptions.ProductDatabaseOperationException;

public interface ProductApprovalService {
	public List<ProductRequest> getRequests(List<Long> productRequestId,List<RequestStatus> status);
	void approveRequest(List<Long> productRequestId, RequestStatus status, Long managerId)
			throws ProductDatabaseOperationException;
	void rejectRequest(List<Long> productRequestId, RequestStatus status, Long managerId);
}