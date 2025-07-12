package com.orders.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.orders.Exceptions.OrderDatabaseOperationException;
import com.orders.dao.OrderDAO;
import com.orders.domain.Order;
import com.orders.domain.OrderItem;
import com.orders.domain.SearchOrderCriteria;
import com.orders.enums.OrderStatus;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static class OrderItemMapper implements RowMapper<OrderItem> {
		@Override
		public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderItem item = new OrderItem();
			item.setOrderItemId(rs.getLong("OrderItemId"));
			item.setOrderId(rs.getLong("OrderId"));
			item.setProductId(rs.getLong("ProductId"));
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
	public Order fetchCustomerOrderWithItems(Long orderId, Long customerId) {
		String orderSql = "SELECT OrderId, UserId, Address, TotalAmount, Status, PlacedAtDate, UpdatedAtDate FROM orders WHERE UserId = :customerId AND OrderId = :orderId";
		MapSqlParameterSource params = new MapSqlParameterSource("orderId", orderId);
		Order order = namedParameterJdbcTemplate.queryForObject(orderSql, params, new OrderMapper());

		String itemsSql = "SELECT OrderItemId, OrderId, ProductId, Quantity, Price, CreatedAt User FROM order_items WHERE OrderId = :orderId";
		List<OrderItem> items = namedParameterJdbcTemplate.query(itemsSql, params, new OrderItemMapper());

		order.setOrderItems(items);
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
		params.addValue("status", orderObj.getStatus());
		params.addValue("placedAtDate", now);

		try {
			int recordInserted = namedParameterJdbcTemplate.update(sql, params);
			if (recordInserted > 0) {
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

	@Override
	public List<Order> fetchCustomerOrders(SearchOrderCriteria criteria) throws OrderDatabaseOperationException {
		StringBuilder sql = new StringBuilder(
				"SELECT OrderId, UserId, Address, TotalAmount, Status, PlacedAtDate, UpdatedAtDate "
						+ "FROM orders WHERE 1=1 " + "AND (:orderIdsFlag = 0 OR OrderId IN (:orderIds)) "
						+ "AND (:orderStatusFlag = 0 OR Status IN (:orderStatuses)) "
						+ "AND (:fromDateFlag = 0 OR PlacedAtDate >= :fromDate) "
						+ "AND (:toDateFlag = 0 OR PlacedAtDate <= :toDate) " + "AND UserId = :customerId "
						+ "ORDER BY PlacedAtDate DESC " + "LIMIT :limit OFFSET :offset");

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
		params.put("orderStatuses", criteria.getOrderStatuses());
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
			return namedParameterJdbcTemplate.query(sql.toString(), params, new OrderMapper());
		} catch (DataAccessException e) {
			throw new OrderDatabaseOperationException("Failed to fetch orders from the database", e);
		}
	}

}
