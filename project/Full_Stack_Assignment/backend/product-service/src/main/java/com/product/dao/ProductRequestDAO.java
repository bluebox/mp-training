package com.product.dao;

import java.util.List;

import com.product.Exceptions.ProductDatabaseOperationException;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;

public interface ProductRequestDAO {
	ProductRequest createProductRequest(ProductRequest request) throws ProductDatabaseOperationException;

	List<ProductRequest> fetchByCriteria(SearchProductRequestCriteria criteria) throws ProductDatabaseOperationException;
	
	void updatePendingRequest(ProductRequest updatedRequest) throws ProductDatabaseOperationException;
}
