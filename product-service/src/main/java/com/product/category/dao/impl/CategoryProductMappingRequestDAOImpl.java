package com.product.category.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.product.category.dao.CategoryProductMappingRequestDAO;
import com.product.category.domain.CategoryProductMappingRequest;
import com.product.enums.RequestStatus;

@Repository
@Transactional
public class CategoryProductMappingRequestDAOImpl implements CategoryProductMappingRequestDAO {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public CategoryProductMappingRequestDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void createMappingRequest(CategoryProductMappingRequest request) {
		String sql = "INSERT INTO category_product_mapping_requests " +
				"(CategoryId, ProductId, RequestedBy, Status, CreatedAtDate) " +
				"VALUES (:categoryId, :productId, :requestedBy, :status, :createdAtDate)";

		Map<String, Object> params = new HashMap<>();
		params.put("categoryId", request.getCategoryId());
		params.put("productId", request.getProductId());
		params.put("requestedBy", request.getRequestedBy());
		params.put("status", RequestStatus.PENDING.getCode());
		params.put("createdAtDate", Timestamp.valueOf(LocalDateTime.now()));

		try {
			namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			throw new Error("Error adding category Product Mapping Request", e);
		}
	}

	@Override
	public void updateRequestStatus(List<Long> requestIds, Integer approvedBy, RequestStatus status) {
		if (requestIds == null || requestIds.isEmpty())
			return;

		String sql = "UPDATE category_product_mapping_requests SET " +
				"Status = :status, ApprovedBy = :approvedBy, UpdatedAtDate = :updatedAtDate " +
				"WHERE MappingRequestId IN (:requestIds)";

		Map<String, Object> params = new HashMap<>();
		params.put("status", status.getCode());
		params.put("approvedBy", approvedBy);
		params.put("updatedAtDate", Timestamp.valueOf(LocalDateTime.now()));
		params.put("requestIds", requestIds);

		try {
			namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			throw new Error("Error updating category Product Mapping request", e);
		}
	}

	@Override
	public List<CategoryProductMappingRequest> findAllRequest(List<Long> requestIds, RequestStatus requestStatus, Long loggedInUser) throws Exception {
		String sql = "SELECT MappingRequestId, CategoryId, ProductId, RequestedBy, ApprovedBy, Status, CreatedAtDate, UpdatedAtDate";

		Map<String, Object> params = new HashMap<>();

		boolean showAll = (requestIds == null || requestIds.isEmpty()) &&
				requestStatus == null &&
				loggedInUser == null;

		if (showAll) {
			sql += " FROM category_product_mapping_requests";
		} else {
			int requestIdsFlag = (requestIds != null && !requestIds.isEmpty()) ? 1 : 0;
			int statusFlag = (requestStatus != null) ? 1 : 0;
			int userIdFlag = (loggedInUser != null) ? 1 : 0;

			sql += " FROM category_product_mapping_requests WHERE 1=1" +
					" AND (:requestIdsFlag = 0 OR MappingRequestId IN (:requestIds))" +
					" AND (:statusFlag = 0 OR Status = :status)" +
					" AND (:userIdFlag = 0 OR RequestedBy = :userId)";

			params.put("requestIdsFlag", requestIdsFlag);
			params.put("statusFlag", statusFlag);
			params.put("userIdFlag", userIdFlag);

			params.put("requestIds", requestIds);
			params.put("status", requestStatus != null ? requestStatus.getCode() : null);
			params.put("userId", loggedInUser);
		}

		try {
			return namedParameterJdbcTemplate.query(sql, params, categoryProductMappingRequestRowMapper());
		} catch (Exception e) {
			throw new Exception("Error while fetching category product mapping requests", e);
		}
	}

	private RowMapper<CategoryProductMappingRequest> categoryProductMappingRequestRowMapper() {
		return (rs, rowNum) -> {
			CategoryProductMappingRequest req = new CategoryProductMappingRequest();
			req.setMappingRequestId(rs.getLong("MappingRequestId"));
			req.setCategoryId(rs.getInt("CategoryId"));
			req.setProductId(rs.getLong("ProductId"));
			req.setRequestedBy(rs.getLong("RequestedBy"));
			req.setApprovedBy(rs.getLong("ApprovedBy"));
			req.setStatus(RequestStatus.valueOf(rs.getString("Status")));
			req.setCreatedAtDate(rs.getTimestamp("CreatedAtDate").toLocalDateTime());
			req.setUpdatedAtDate(
					rs.getTimestamp("UpdatedAtDate") != null ? rs.getTimestamp("UpdatedAtDate").toLocalDateTime() : null);
			return req;
		};
	}
}
