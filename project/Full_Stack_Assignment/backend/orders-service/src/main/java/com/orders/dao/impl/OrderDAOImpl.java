package com.orders.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.orders.dao.OrderDAO;
import com.orders.domain.Order;
import com.orders.item.dao.OrderItemDAO;
import com.orders.item.domain.OrderItem;
import com.orders.domain.SearchOrderCriteria;
import com.orders.enums.OrderStatus;
import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private OrderItemDAO orderItemDAO;

	private static class OrderItemMapper implements RowMapper<OrderItem> {
		@Override
		public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderItem item = new OrderItem();
			item.setOrderItemId(rs.getLong("OrderItemId"));
			item.setOrderId(rs.getLong("OrderId"));
			item.setProductId(rs.getLong("ProductId"));
			item.setProductName(rs.getString("ProductName"));
			item.setQuantity(rs.getInt("Quantity"));
			item.setPrice(rs.getBigDecimal("Price"));
			Timestamp createdTs = rs.getTimestamp("CreatedAtDate");
			item.setCreatedAt(createdTs != null ? createdTs.toLocalDateTime() : null);
			return item;
		}
	}

	private static class OrderMapper implements RowMapper<Order> {
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setOrderId(rs.getLong("OrderId"));
			order.setUserId(rs.getLong("UserId"));
			order.setAddress(rs.getString("Address"));
			order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
			String orderStatus = rs.getString("Status");
			if ("P".equalsIgnoreCase(orderStatus)) {
				order.setStatus(OrderStatus.PROCESSING);
			} else if ("C".equalsIgnoreCase(orderStatus)) {
				order.setStatus(OrderStatus.CANCELLED);
			} else {
				order.setStatus(OrderStatus.DELIVERED);
			}
			order.setPlacedAtDate(
					rs.getTimestamp("PlacedAtDate") != null ? rs.getTimestamp("PlacedAtDate").toLocalDateTime() : null);
			order.setUpdatedAtDate(
					rs.getTimestamp("UpdatedAtDate") != null ? rs.getTimestamp("UpdatedAtDate").toLocalDateTime()
							: null);
			return order;
		}
	}

	@Override
	public Order fetchCustomerOrderWithItems(Long orderId, Long customerId)
			throws OrderNotFoundException, OrderDatabaseOperationException {
		String orderSql = "SELECT OrderId, UserId, Address, TotalAmount, Status, PlacedAtDate, UpdatedAtDate FROM orders WHERE UserId = :customerId AND OrderId = :orderId";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orderId", orderId);
		params.addValue("customerId", customerId);

		Order order;
		try {
			order = namedParameterJdbcTemplate.queryForObject(orderSql, params, new OrderMapper());
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			throw new OrderNotFoundException("No order found for given Order ID and Customer ID.");
		} catch (DataAccessException e) {
			log.error("Database error while fetching order", e);
			throw new OrderDatabaseOperationException("Database error occurred while fetching the order.", e);
		}

		try {
			List<OrderItem> items = orderItemDAO.fetchOrderItemsByOrderId(orderId);
			order.setOrderItems(items);
		} catch (DataAccessException e) {
			log.error("Failed to fetch order items", e);
			throw new OrderDatabaseOperationException("Failed to fetch order items.", e);
		}

		return order;
	}

	@Override
	public Order createOrder(Order orderObj) throws OrderDatabaseOperationException {
		String sql = "INSERT INTO orders " + "(UserId, Address, TotalAmount, Status, PlacedAtDate)"
				+ "VALUES(:userId, :address, :totalAmount, :status, :placedAtDate)";
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userId", orderObj.getUserId());
		params.addValue("address", orderObj.getAddress());
		params.addValue("totalAmount", orderObj.getTotalAmount());
		params.addValue("status", orderObj.getStatus().getCode());
		params.addValue("placedAtDate", now);

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			int recordInserted = namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "OrderId" });
			if (recordInserted > 0) {
				Number generatedOrderId = keyHolder.getKey();
				if (generatedOrderId != null) {
					orderObj.setOrderId(generatedOrderId.longValue());
				}
				orderObj.setStatus(OrderStatus.PROCESSING);
				orderObj.setPlacedAtDate(now.toLocalDateTime());
				return orderObj;
			} else {
				throw new OrderDatabaseOperationException("Failed to insert order, no rows affected");
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new OrderDatabaseOperationException("Database error occured while creating the order.", e);
		}
	}

	public List<Order> fetchCustomerOrders(SearchOrderCriteria criteria) throws OrderDatabaseOperationException {
		String sql = "SELECT OrderId, UserId, Address, TotalAmount, Status, PlacedAtDate, UpdatedAtDate "
				+ "FROM orders WHERE 1=1 " + "AND (:orderIdsFlag = 0 OR OrderId IN (:orderIds)) "
				+ "AND (:orderStatusFlag = 0 OR Status IN (:orderStatuses)) "
				+ "AND (:fromDateFlag = 0 OR PlacedAtDate >= :fromDate) "
				+ "AND (:toDateFlag = 0 OR PlacedAtDate <= :toDate) " + "AND UserId = :customerId "
				+ "ORDER BY PlacedAtDate DESC " + "LIMIT :limit OFFSET :offset";

		Map<String, Object> params = new HashMap<>();

		int orderIdsFlag = (criteria.getOrderIds() != null && !criteria.getOrderIds().isEmpty()) ? 1 : 0;
		int orderStatusFlag = (criteria.getOrderStatuses() != null && !criteria.getOrderStatuses().isEmpty()) ? 1 : 0;
		int fromDateFlag = (criteria.getFromDate() != null) ? 1 : 0;
		int toDateFlag = (criteria.getToDate() != null) ? 1 : 0;

		params.put("orderIdsFlag", orderIdsFlag);
		params.put("orderStatusFlag", orderStatusFlag);
		params.put("fromDateFlag", fromDateFlag);
		params.put("toDateFlag", toDateFlag);

		params.put("orderIds", criteria.getOrderIds());
		List<String> statusCodes = null;
		if (criteria.getOrderStatuses() != null && !criteria.getOrderStatuses().isEmpty()) {
			statusCodes = criteria.getOrderStatuses().stream().map(OrderStatus::getCode).collect(Collectors.toList());
		}
		params.put("orderStatuses", statusCodes);
		params.put("fromDate", criteria.getFromDate());
		params.put("toDate", criteria.getToDate());

		params.put("customerId", criteria.getCustomerId());

		int pageSize = (criteria.getPageSize() != null && criteria.getPageSize() > 0) ? criteria.getPageSize() : 10;
		int pageNumber = (criteria.getPageNumber() != null && criteria.getPageNumber() >= 0) ? criteria.getPageNumber()
				: 0;
		int offset = pageNumber * pageSize;

		params.put("limit", pageSize);
		params.put("offset", offset);

		try {
			return namedParameterJdbcTemplate.query(sql, params, new OrderMapper());
		} catch (DataAccessException e) {
			throw new OrderDatabaseOperationException("Failed to fetch orders from the database", e);
		}
	}

	@Override
	public void updateMyOrder(SearchOrderCriteria criteria) throws OrderDatabaseOperationException {
		String sql = "UPDATE orders SET " + "Status = CASE "
				+ "WHEN (:addressFlag = 0 AND UserId = :userId AND OrderId IN (:orderIds) AND Status = 'P') THEN 'C' "
				+ "ELSE Status END, " + "Address = CASE "
				+ "WHEN (:addressFlag = 1 AND UserId = :userId AND OrderId IN (:orderIds)) THEN :address "
				+ "ELSE Address END";

		Map<String, Object> params = new HashMap<>();
		params.put("addressFlag", criteria.getAddress() != null && !criteria.getAddress().trim().isEmpty() ? 1 : 0);
		params.put("userId", criteria.getCustomerId());
		params.put("orderIds", criteria.getOrderIds());
		params.put("address", criteria.getAddress());

		try {
			namedParameterJdbcTemplate.update(sql, params);
		} catch (DataAccessException e) {
			throw new OrderDatabaseOperationException("Failed to fetch orders from the database", e);
		}
	}

}
