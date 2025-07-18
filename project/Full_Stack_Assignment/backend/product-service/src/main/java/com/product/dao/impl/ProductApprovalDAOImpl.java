package com.product.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.product.dao.ProductApprovalDAO;
import com.product.domain.ProductRequest;
import com.product.enums.RequestStatus;

@Repository
public class ProductApprovalDAOImpl implements ProductApprovalDAO {

	private static final String SELECT_REQUESTS_QUERY = "SELECT ProductRequestId, ProductName, Description, Price, "
			+ "Quantity, RequestedBy, Status, ApprovedBy, CreatedAtDate, UpdatedAtDate " + "FROM product_requests "
			+ "WHERE ((0 = :flagIds) OR (ProductRequestId IN (:ids))) "
			+ "AND ((0 = :flagStatus) OR (Status IN (:status)))";

	private static final String UPDATE_REQUEST_QUERY = "UPDATE product_requests SET " + "Status = :status, "
			+ "ApprovedBy = :approvedBy, " + "UpdatedAtDate = :updatedAtDate "
			+ "WHERE ProductRequestId = :productRequestId";

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public ProductApprovalDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ProductRequest> getRequests(List<Long> productRequestId, Integer flagIds, List<RequestStatus> status,
			Integer flagStatus) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("flagIds", flagIds);
		params.addValue("ids", productRequestId);
		params.addValue("flagStatus", flagStatus);
		params.addValue("status", status.stream().map(RequestStatus::getCode).collect(Collectors.toList()));

		return jdbcTemplate.query(SELECT_REQUESTS_QUERY, params, new ProductApprovalMapper());
	}

	@Override
	public int[] updateRequest(List<Long> productRequestIds, RequestStatus status, Long managerId) {
		List<MapSqlParameterSource> paramSources = productRequestIds.stream()
				.map(id -> new MapSqlParameterSource().addValue("status", status.getCode())
						.addValue("approvedBy", managerId).addValue("updatedAtDate", LocalDateTime.now())
						.addValue("productRequestId", id))
				.collect(Collectors.toList());

		int[] records = jdbcTemplate.batchUpdate(UPDATE_REQUEST_QUERY,
				paramSources.toArray(new MapSqlParameterSource[0]));

		boolean allFailed = records.length == 0 || productRequestIds.size() != records.length
				|| java.util.Arrays.stream(records).allMatch(count -> count == 0);

		if (allFailed) {
			throw new RuntimeException("No requests were updated. Invalid IDs? " + productRequestIds);
		}

		return records;
	}

	private static class ProductApprovalMapper implements RowMapper<ProductRequest> {
		@Override
		public ProductRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductRequest request = new ProductRequest();
			request.setProductRequestId(rs.getLong("ProductRequestId"));
			request.setProductName(rs.getString("ProductName"));
			request.setPrice(rs.getBigDecimal("Price"));
			request.setDescription(rs.getString("Description"));
			request.setQuantity(rs.getInt("Quantity"));
			request.setRequestedBy(rs.getLong("RequestedBy"));

			String status = rs.getString("Status");
			if ("A".equalsIgnoreCase(status)) {
				request.setStatus(RequestStatus.APPROVED);
			} else if ("D".equalsIgnoreCase(status)) {
				request.setStatus(RequestStatus.DECLINED);
			} else {
				request.setStatus(RequestStatus.PENDING);
			}

			Object approvedBy = rs.getObject("ApprovedBy");
			request.setApprovedBy(Objects.nonNull(approvedBy) ? rs.getLong("ApprovedBy") : null);

			if (rs.getTimestamp("CreatedAtDate") != null) {
				request.setCreatedAtDate(rs.getTimestamp("CreatedAtDate").toLocalDateTime());
			}

			if (rs.getTimestamp("UpdatedAtDate") != null) {
				request.setUpdatedAtDate(rs.getTimestamp("UpdatedAtDate").toLocalDateTime());
			}

			return request;
		}
	}
}
