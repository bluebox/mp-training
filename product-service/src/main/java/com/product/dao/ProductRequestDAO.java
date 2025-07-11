package com.product.dao;

import java.util.List;

import com.product.Exceptions.ProductRequestDatabaseOperationException;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;

public interface ProductRequestDAO {
	ProductRequest createProductRequest(ProductRequest request) throws ProductRequestDatabaseOperationException;

	List<ProductRequest> fetchByCriteria(SearchProductRequestCriteria criteria) throws ProductRequestDatabaseOperationException;
	
	void updatePendingRequest(ProductRequest updatedRequest) throws ProductRequestDatabaseOperationException;
}
