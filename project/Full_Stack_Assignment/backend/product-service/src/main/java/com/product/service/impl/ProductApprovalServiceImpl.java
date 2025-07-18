package com.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.Exceptions.ProductDatabaseOperationException;
import com.product.dao.ProductApprovalDAO;
import com.product.dao.ProductRequestDAO;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;
import com.product.enums.RequestStatus;
import com.product.service.ProductApprovalService;
import com.product.service.ProductService;

@Service
public class ProductApprovalServiceImpl implements ProductApprovalService {

    @Autowired
    ProductApprovalDAO productApprovalDAO;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRequestDAO productRequestDAO;

    @Override
    public List<ProductRequest> getRequests(List<Long> productRequestId, List<RequestStatus> status) {
        int flagIds = 1;
        int flagStatus = 1;

        if (productRequestId == null || productRequestId.isEmpty()) {
            flagIds = 0;
        }
        if (status == null) {
            flagStatus = 0;
        }

        return productApprovalDAO.getRequests(productRequestId, flagIds, status, flagStatus);
    }

    @Override
    public void approveRequest(List<Long> productRequestId, RequestStatus status, Long managerId)
            throws ProductDatabaseOperationException {
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

        SearchProductRequestCriteria sprc = new SearchProductRequestCriteria(); // method-local
        sprc.setRequestIds(productRequestId);
        sprc.setStatus(status);

        List<ProductRequest> productRequest = productRequestDAO.fetchByCriteria(sprc);
        productService.insertProduct(productRequest);
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
