package com.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.dao.impl.ProductApprovalDAOImpl;
import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;
import com.product.service.ProductApprovalService;

@Service
public class ProductApprovalServiceImpl implements ProductApprovalService{

	ProductApprovalDAOImpl productApprovalDAOImpl = new ProductApprovalDAOImpl();
	@Override
	public List<ProductRequest> getRequests(List<Long> productRequestId,List<RequestStatus> status) {
		return null;
	}

	@Override
	public void updateRequest(List<Long> productRequestId, RequestStatus status, Long managerId) {
		
	}
	
}
