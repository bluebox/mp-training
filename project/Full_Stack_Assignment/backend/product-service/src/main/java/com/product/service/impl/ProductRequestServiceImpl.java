package com.product.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.product.Exceptions.ProductDatabaseOperationException;
import com.product.Exceptions.InvalidProductRequestException;
import com.product.Exceptions.RequestNotFoundException;
import com.product.dao.ProductRequestDAO;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;
import com.product.service.ProductRequestService;
import com.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductRequestServiceImpl implements ProductRequestService {

	private final ProductRequestDAO productRequestDAO;

	private final ProductService productService;

	@Override
	public ProductRequest generateProductRequest(ProductRequest request)
			throws ProductDatabaseOperationException, InvalidProductRequestException {
		validateRequestFields(request);
		try {
			return productRequestDAO.createProductRequest(request);
		} catch (DataAccessException e) {
			throw new ProductDatabaseOperationException("Error while creating product request.", e);
		}
	}

	@Override
	public List<ProductRequest> getRequestsByCriteria(SearchProductRequestCriteria criteria)
			throws ProductDatabaseOperationException, RequestNotFoundException {
		try {
			if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
				criteria.setPageNumber(0);
			}
			if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
				criteria.setPageSize(10);
			}
			List<ProductRequest> requests = productRequestDAO.fetchByCriteria(criteria);
			if (requests == null || requests.isEmpty()) {
				throw new RequestNotFoundException("No product requests found for the given criteria.");
			}
			return requests;
		} catch (DataAccessException e) {
			throw new ProductDatabaseOperationException("Error while fetching product requests.", e);
		}
	}

	@Override
	public void updatePendingProductRequest(ProductRequest updatedRequest)
			throws ProductDatabaseOperationException, InvalidProductRequestException {
		if (updatedRequest.getProductRequestId() == null || updatedRequest.getProductRequestId() <= 0) {
			throw new InvalidProductRequestException("Request ID must be a valid positive number.");
		}
		validateRequestFields(updatedRequest);

		try {
			productRequestDAO.updatePendingRequest(updatedRequest);
		} catch (DataAccessException e) {
			throw new ProductDatabaseOperationException("Error while updating pending product request.", e);
		}
	}

	private void validateRequestFields(ProductRequest request) throws InvalidProductRequestException {
		if (request.getProductName() == null || request.getProductName().trim().length() < 3
				|| request.getProductName().length() > 150) {
			throw new InvalidProductRequestException("Product name must be between 3 and 150 characters.");
		}

		if (request.getDescription() == null || request.getDescription().trim().length() < 10) {
			throw new InvalidProductRequestException("Product description must be at least 10 characters.");
		}

		if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidProductRequestException("Product price must be a positive value.");
		}

		if (request.getQuantity() <= 0) {
			throw new InvalidProductRequestException("Quantity must be greater than zero.");
		}

		if (request.getRequestedBy() == null || request.getRequestedBy() <= 0) {
			throw new InvalidProductRequestException("RequestedBy (user ID) must be provided and positive.");
		}

		List<String> existingProductNames = productService.getProducts(null).stream()
				.map(product -> product.getProductName().trim().toLowerCase()).collect(Collectors.toList());
		if (existingProductNames.contains(request.getProductName()) && existingProductNames.size() > 0) {
			throw new InvalidProductRequestException(
					"Product with name '" + request.getProductName() + "' already exists in the databsase.");
		}
	}
}
