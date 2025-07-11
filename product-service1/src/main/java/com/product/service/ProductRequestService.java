package com.product.service;

import java.util.List;

import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;

public interface ProductRequestService {
	ProductRequest generateProductRequest(ProductRequest request);

	List<ProductRequest> getRequestsByCriteria(SearchProductRequestCriteria criteria);

	void updatePendingProductRequest(ProductRequest updatedRequest);
}
