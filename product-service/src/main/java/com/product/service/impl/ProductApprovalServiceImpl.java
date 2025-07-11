package com.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.Exceptions.ProductRequestDatabaseOperationException;
import com.product.dao.ProductApprovalDAO;
import com.product.dao.ProductRequestDAO;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;
import com.product.enums.RequestStatus;
import com.product.service.ProductApprovalService;
import com.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductApprovalServiceImpl implements ProductApprovalService{

	@Autowired
	ProductApprovalDAO productApprovalDAO;
	@Autowired
	ProductService productService;
	
	SearchProductRequestCriteria sprc;
	@Autowired
	ProductRequestDAO productRequestDAO;
	@Override
	public List<ProductRequest> getRequests(List<Long> productRequestId,List<RequestStatus> status) {
		Integer flagIds = 1;
		if (productRequestId.isEmpty()) {
			flagIds = 0;
		}
		Integer flagStatus = 1;
		if (status.isEmpty()) {
			flagStatus = 0;
		}
		return productApprovalDAO.getRequests(productRequestId, flagIds, status, flagStatus);
	}

	@Override
	public void approveRequest(List<Long> productRequestId, RequestStatus status, Long managerId) throws ProductRequestDatabaseOperationException {
		if (productRequestId == null || productRequestId.isEmpty()) {
            throw new IllegalArgumentException("Product request IDs must not be null or empty.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null.");
        }
        if (managerId == null) {
            throw new IllegalArgumentException("Manager ID must not be null.");
        }

        Integer records = productApprovalDAO.updateRequest(productRequestId, status, managerId);
        if (records != 0) {
        	sprc.setRequestIds(productRequestId);
        	sprc.setStatus(status);
        	List<ProductRequest> productRequest = productRequestDAO.fetchByCriteria(sprc);
        	productService.insertProduct(productRequest);
        }
        
    }
	
	@Override
	public void rejectRequest(List<Long> productRequestId, RequestStatus status, Long managerId) {
		if (productRequestId == null || productRequestId.isEmpty()) {
            throw new IllegalArgumentException("Product request IDs must not be null or empty.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null.");
        }
        if (managerId == null) {
            throw new IllegalArgumentException("Manager ID must not be null.");
        }

        productApprovalDAO.updateRequest(productRequestId, status, managerId);
    }
	
}
