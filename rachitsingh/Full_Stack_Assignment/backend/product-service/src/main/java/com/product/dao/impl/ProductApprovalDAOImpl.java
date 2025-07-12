package com.product.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.product.dao.ProductApprovalDAO;
import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;

public class ProductApprovalDAOImpl implements ProductApprovalDAO {

	private JdbcTemplate jdbcTemplate;

	private static class ProductApprovalMapper implements RowMapper<ProductRequest> {
		@Override
		public ProductRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductRequest productRequest = new ProductRequest();

			productRequest.setProductRequestId(rs.getLong("ProductRequestId"));
			productRequest.setProductName(rs.getString("ProductName"));
			productRequest.setPrice(rs.getBigDecimal("Price"));
			productRequest.setDescription(rs.getString("Description"));
			productRequest.setQuantity(rs.getInt("Quantity"));
			productRequest.setRequestedBy(rs.getLong("RequestedBy"));

			String productRequestStatus = rs.getString("Status");
			if ("A".equalsIgnoreCase(productRequestStatus)) {
				productRequest.setStatus(RequestStatus.APPROVED);
			} else if ("D".equalsIgnoreCase(productRequestStatus)) {
				productRequest.setStatus(RequestStatus.DECLINED);
			} else {
				productRequest.setStatus(RequestStatus.PENDING);
			}
			productRequest.setApprovedBy(rs.getObject("ApprovedBy") != null ? rs.getLong("ApprovedBy") : null);

			productRequest.setCreatedAtDate(
					rs.getTimestamp("CreatedAtDate") != null ? rs.getTimestamp("CreatedAtDate").toLocalDateTime()
							: null);
			productRequest.setUpdatedAtDate(
					rs.getTimestamp("UpdatedAtDate") != null ? rs.getTimestamp("UpdatedAtDate").toLocalDateTime()
							: null);
			return productRequest;
		}
	}

	@Override
	public List<ProductRequest> getRequests(List<Long> productRequestId,Integer flagIds ,List<RequestStatus> status,Integer flagStatus) {
		String sqlQuery = "SELECT ProductRequestId, ProductName, Description, Price,"+
				" Quantity, RequestedBy, Status, ApprovedBy, CreatedAtDate, "+
				"UpdatedAtDate FROM product_requests WHERE "+
				"((0 = :flagIds) OR (ProductRequestId IN (:ids))) AND "+
				"((0 = :flagStatus) OR (Status IN (:status)))";

		    MapSqlParameterSource parameters = new MapSqlParameterSource();
		    parameters.addValue("flagIds", flagIds);
		    parameters.addValue("ids",productRequestId);
			parameters.addValue("flagStatus", flagStatus);
		    parameters.addValue("status",status);
		    return jdbcTemplate.query(sqlQuery,new ProductApprovalMapper(),parameters);
		    
	}

	@Override
	public void updateRequest(List<Long> productRequestId, RequestStatus status, Long managerId) {
		String sql = "UPDATE product_requests SET Status = ?, ApprovedBy = ?, UpdatedAtDate = ? WHERE ProductRequestId = ?";
		int records = jdbcTemplate.update(sql, status.getCode(), managerId, LocalDateTime.now(), productRequestId);
		if (records == 0) {
			throw new RuntimeException("No request found with ID " + productRequestId + "to update status.");

		}
	}

}
