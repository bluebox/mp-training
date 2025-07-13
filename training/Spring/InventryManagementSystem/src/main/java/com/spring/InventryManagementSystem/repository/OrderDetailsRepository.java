package com.spring.InventryManagementSystem.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.spring.InventryManagementSystem.domain.OrderDetails;
import com.spring.InventryManagementSystem.interfaces.repository.OrderDetailsRepositoryInterface;
import com.spring.InventryManagementSystem.row.mappers.OrderDetailsRowMapper;

@Repository
public class OrderDetailsRepository implements OrderDetailsRepositoryInterface {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public OrderDetailsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int insertOrderDetails(OrderDetails orderDetails) {
		String sql = "INSERT INTO OrderDetails (orderDate,orderCost,orderDiscount,orderStatus,orderSupplier) VALUES (:orderDate,:orderCost,:orderDiscount,:orderStatus,:orderSupplier)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orderDate", orderDetails.getOrderDate());
		params.addValue("orderCost", orderDetails.getOrderCost());
		params.addValue("orderDiscount", orderDetails.getOrderDiscount());
		params.addValue("orderStatus", orderDetails.getOrderStatus());
		params.addValue("orderSupplier", orderDetails.getOrderSupplier());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "orderId" });
		return keyHolder.getKey().intValue();
	}

	@Override
	public List<OrderDetails> pendingOrders() throws SQLException {
		String sql = "SELECT * FROM OrderDetails WHERE status = :status";
		return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), new OrderDetailsRowMapper());
	}

	@Override
	public List<OrderDetails> allOrdersAdmin() throws SQLException {
		String sql = "SELECT * FROM OrderDetails WHERE status <> 'WITHDRAWN'";
		return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), new OrderDetailsRowMapper());
	}

	@Override
	public List<OrderDetails> allOrders() throws SQLException {
		String sql = "SELECT * FROM OrderDetails";
		return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), new OrderDetailsRowMapper());
	}

	@Override
	public boolean withdrawOrder(Integer orderId) throws SQLException {
		String sql = "UPDATE OrderDetails SET status = 'WITHDRAWN' WHERE status='PENDING' AND orderId = :orderId";
		MapSqlParameterSource params= new MapSqlParameterSource();
		params.addValue("orderId", orderId);
		return namedParameterJdbcTemplate.update(sql, params) ==1;
	}

}
