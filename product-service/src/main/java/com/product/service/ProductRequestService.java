package com.product.service;

import java.util.List;

import com.product.Exceptions.InvalidProductRequestException;
import com.product.Exceptions.ProductRequestDatabaseOperationException;
import com.product.Exceptions.RequestNotFoundException;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;

public interface ProductRequestService {
	ProductRequest generateProductRequest(ProductRequest request)
			throws ProductRequestDatabaseOperationException, InvalidProductRequestException;

	List<ProductRequest> getRequestsByCriteria(SearchProductRequestCriteria criteria)
			throws ProductRequestDatabaseOperationException, RequestNotFoundException;

	void updatePendingProductRequest(ProductRequest updatedRequest)
			throws ProductRequestDatabaseOperationException, InvalidProductRequestException;
}
