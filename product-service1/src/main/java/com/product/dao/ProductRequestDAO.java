package com.product.dao;

import java.util.List;

import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;

public interface ProductRequestDAO {
	ProductRequest createProductRequest(ProductRequest request);

	List<ProductRequest> fetchByCriteria(SearchProductRequestCriteria criteria);
	
	void updatePendingRequest(ProductRequest updatedRequest);
//	void updateStatus(Long requestId, RequestStatus status, Long approvedBy);
}
