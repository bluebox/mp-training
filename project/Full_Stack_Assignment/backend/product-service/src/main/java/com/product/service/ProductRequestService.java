package com.product.service;

import java.util.List;

import com.product.Exceptions.InvalidProductRequestException;
import com.product.Exceptions.ProductDatabaseOperationException;
import com.product.Exceptions.RequestNotFoundException;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;

public interface ProductRequestService {
	ProductRequest generateProductRequest(ProductRequest request)
			throws ProductDatabaseOperationException, InvalidProductRequestException;

	List<ProductRequest> getRequestsByCriteria(SearchProductRequestCriteria criteria)
			throws ProductDatabaseOperationException, RequestNotFoundException;

	void updatePendingProductRequest(ProductRequest updatedRequest)
			throws ProductDatabaseOperationException, InvalidProductRequestException;
}
