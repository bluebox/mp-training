package com.product.category.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.category.domain.CategoryProductMappingRequest;

@Service
public interface CategoryProductMappingRequestDAO {

	void createMapping(CategoryProductMappingRequest request);

    void updateRequestStatus(Integer mappingRequestId, Character status);

    List<CategoryProductMappingRequest> findAllRequest();
}
