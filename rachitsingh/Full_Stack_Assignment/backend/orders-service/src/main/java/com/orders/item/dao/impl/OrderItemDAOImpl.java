package com.orders.item.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.orders.exceptions.OrderDatabaseOperationException;
import com.orders.exceptions.OrderNotFoundException;
import com.orders.item.dao.OrderItemDAO;
import com.orders.item.domain.OrderItem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class OrderItemDAOImpl implements OrderItemDAO {

	private final NamedParameterJdbcTemplate jdbc;

	private static class OrderItemMapper implements RowMapper<OrderItem> {
		@Override
		public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderItem orderItem = new OrderItem();

			orderItem.setOrderId(rs.getLong("OrderId"));
			orderItem.setOrderItemId(rs.getLong("OrderItemId"));
			orderItem.setPrice(rs.getBigDecimal("Price"));
			orderItem.setProductId(rs.getLong("ProductId"));
			orderItem.setProductName(rs.getString("ProductName"));
			orderItem.setQuantity(rs.getInt("Quantity"));
			Timestamp createdTs = rs.getTimestamp("CreatedAt");
			orderItem.setCreatedAt(createdTs != null ? createdTs.toLocalDateTime() : null);
			return orderItem;
		}
	}

	@Override
	public void storeOrderItems(List<OrderItem> items) throws OrderDatabaseOperationException {
		String sql = "INSERT INTO order_items (OrderId, ProductId, ProductName, Quantity, Price, CreatedAt) "
				+ "VALUES (:orderId, :productId, :productName, :quantity, :price, :createdAt)";

		try {
			for (OrderItem item : items) {
				Map<String, Object> params = new HashMap<>();
				params.put("orderId", item.getOrderId());
				params.put("productId", item.getProductId());
				params.put("productName", item.getProductName());
				params.put("quantity", item.getQuantity());
				params.put("price", item.getPrice());
				params.put("createdAt", item.getCreatedAt());

				jdbc.update(sql, params);
			}
		} catch (DataAccessException e) {
			log.error("Error while storing order items", e);
			throw new OrderDatabaseOperationException("Failed to store order items", e);
		}
	}

	@Override
	public List<OrderItem> fetchOrderItemsByOrderId(Long orderId) throws OrderNotFoundException, OrderDatabaseOperationException {
		String sql = "SELECT OrderItemId, OrderId, ProductId, ProductName, Quantity, Price, CreatedAt "
				+ "FROM order_items WHERE OrderId = :orderId";

		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);

		try {
			List<OrderItem> items = jdbc.query(sql, params, new OrderItemMapper());
			if (items == null || items.isEmpty()) {
				throw new OrderNotFoundException("No items found for order ID: " + orderId);
			}
			return items;
		} catch (DataAccessException e) {
			log.error("Error while fetching order items for orderId: {}", orderId, e);
			throw new OrderDatabaseOperationException("Failed to fetch order items from database", e);
		}
	}
}
