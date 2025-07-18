package com.product.dao;

import java.util.List;

import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;

public interface ProductApprovalDAO {
	public List<ProductRequest> getRequests(List<Long> productRequestId, Integer flagIds, List<RequestStatus> status,
			Integer flagStatus);

	public int[] updateRequest(List<Long> productRequestId, RequestStatus status, Long managerId);
}