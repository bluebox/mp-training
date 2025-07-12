package com.product.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.product.dao.ProductRequestDAO;
import com.product.domain.ProductRequest;
import com.product.domain.SearchProductRequestCriteria;
import com.product.enums.RequestStatus;

import lombok.extern.slf4j.Slf4j;
import com.product.Exceptions.ProductDatabaseOperationException;

@Repository
@Slf4j
public class ProductRequestDAOImpl implements ProductRequestDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static class ProductRequestMapper implements RowMapper<ProductRequest> {
		@Override
		public ProductRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductRequest productRequest = new ProductRequest();

			productRequest.setProductRequestId(rs.getLong("ProductRequestId"));
			productRequest.setProductName(rs.getString("ProductName"));
			productRequest.setPrice(rs.getBigDecimal("Price"));
			productRequest.setDescription(rs.getString("Description"));
			productRequest.setQuantity(rs.getInt("Quantity"));
			productRequest.setRequestedBy(rs.getLong("RequestedBy"));

			char productRequestStatus = rs.getString("Status").charAt(0);
			if (productRequestStatus == 'A' || productRequestStatus == 'a') {
				productRequest.setStatus(RequestStatus.APPROVED);
			} else if (productRequestStatus == 'D' || productRequestStatus == 'd') {
				productRequest.setStatus(RequestStatus.DECLINED);
			} else {
				productRequest.setStatus(RequestStatus.PENDING);
			}

			productRequest.setApprovedBy(rs.getObject("ApprovedBy") != null ? rs.getLong("ApprovedBy") : null);

			Timestamp createdTs = rs.getTimestamp("CreatedAtDate");
			Timestamp updatedTs = rs.getTimestamp("UpdatedAtDate");

			productRequest.setCreatedAtDate(createdTs != null ? createdTs.toLocalDateTime() : null);
			productRequest.setUpdatedAtDate(updatedTs != null ? updatedTs.toLocalDateTime() : null);

			return productRequest;
		}
	}

	@Override
	public ProductRequest createProductRequest(ProductRequest requestObj) throws ProductDatabaseOperationException {
		requestObj.setStatus(RequestStatus.PENDING);
		String sql = "INSERT INTO product_requests "
				+ "(ProductName, Description, Price, Quantity, RequestedBy, CreatedAtDate) "
				+ "VALUES (:name, :description, :price, :qty, :requestedBy, :createdAt)";

		Timestamp now = Timestamp.valueOf(LocalDateTime.now());

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", requestObj.getProductName());
		params.addValue("description", requestObj.getDescription());
		params.addValue("price", requestObj.getPrice());
		params.addValue("qty", requestObj.getQuantity());
		params.addValue("requestedBy", requestObj.getRequestedBy());
		params.addValue("createdAt", now);

		try {
			int recordInserted = namedParameterJdbcTemplate.update(sql, params);
			if (recordInserted > 0) {
				requestObj.setStatus(RequestStatus.PENDING);
				requestObj.setCreatedAtDate(now.toLocalDateTime());
				return requestObj;
			} else {
				throw new ProductDatabaseOperationException("Failed to insert product request, no rows affected");
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new ProductDatabaseOperationException("Database error occurred while creating product request", e);
		}
	}

	@Override
	public List<ProductRequest> fetchByCriteria(SearchProductRequestCriteria criteria)
			throws ProductDatabaseOperationException {
		String sql = "SELECT ProductRequestId, ProductName, Description, Price, Quantity, RequestedBy, Status, ApprovedBy, CreatedAtDate, UpdatedAtDate FROM product_requests WHERE (:requestIdsFlag = 0 OR ProductRequestId IN (:requestIds)) AND (:productNamesFlag = 0 OR ProductName IN (:productNames)) AND (:userIdFlag = 0 OR RequestedBy = :userId) AND (:statusFlag = 0 OR Status = :status) ORDER BY CreatedAtDate DESC LIMIT :limit OFFSET :offset";

		Map<String, Object> params = new HashMap<>();

		int requestIdsFlag = (criteria.getRequestIds() != null && criteria.getRequestIds().size() > 0) ? 1 : 0;
		int productNamesFlag = (criteria.getProductNames() != null && criteria.getProductNames().size() > 0) ? 1 : 0;
		int userIdFlag = (criteria.getLoggedInUserId() != null) ? 1 : 0;
		int statusFlag = (criteria.getStatus() != null) ? 1 : 0;
		int pageNumberFlag = (criteria.getPageNumber() != null && criteria.getPageNumber() >= 0) ? 1 : 0;
		int pageSizeFlag = (criteria.getPageSize() != null && criteria.getPageSize() > 0) ? 1 : 0;
		int offsetFlag = pageNumberFlag * pageSizeFlag;

		params.put("requestIdsFlag", requestIdsFlag);
		params.put("productNamesFlag", productNamesFlag);
		params.put("userIdFlag", userIdFlag);
		params.put("statusFlag", statusFlag);
		params.put("offsetFlag", offsetFlag);
		params.put("requestIds", criteria.getRequestIds());
		params.put("productNames", criteria.getProductNames());
		params.put("userId", criteria.getLoggedInUserId());
		params.put("status", criteria.getStatus() != null ? criteria.getStatus().getCode() : null);
		params.put("limit", criteria.getPageSize());
		params.put("offset", criteria.getPageNumber());

		try {
			return namedParameterJdbcTemplate.query(sql.toString(), params, new ProductRequestMapper());
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new ProductDatabaseOperationException("Error while fetching product requests from database", e);
		}
	}

	@Override
	public void updatePendingRequest(ProductRequest RequestToUpdate) throws ProductDatabaseOperationException {
		String sql = "UPDATE product_requests SET ProductName = :name, Description = :description, Price = :price, Quantity = :qty, UpdatedAtDate = :updatedAt WHERE ProductRequestId = :requestId AND Status = :pendingStatus";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", RequestToUpdate.getProductName());
		params.addValue("description", RequestToUpdate.getDescription());
		params.addValue("price", RequestToUpdate.getPrice());
		params.addValue("qty", RequestToUpdate.getQuantity());
		params.addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()));
		params.addValue("requestId", RequestToUpdate.getProductRequestId());
		params.addValue("pendingStatus", RequestStatus.PENDING.getCode());

		try {
			int updated = namedParameterJdbcTemplate.update(sql, params);
			if (updated == 0) {
				throw new ProductDatabaseOperationException(
						"Cannot update. Either the request doesn't exist or is not in pending state.");
			}
		} catch (DataAccessException e) {
			throw new ProductDatabaseOperationException("Database error while updating product request.", e);
		}
	}

}
