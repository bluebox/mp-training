package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.custom_classes.OrdersSearch;
import com.rms.restaurant_management_system_backend.dao.OrdersDao;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.rowmappers.OrdersRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public OrdersDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int addOrder(Orders order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SqlQueries.ORDER_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getCustomerId());
			ps.setInt(2, order.getWaiterId());
			ps.setDate(3, Date.valueOf(order.getOrderDate()));
			ps.setDouble(4, order.getAmount());
			ps.setString(5, order.getStatus().getStatus());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public int updateAmount(Orders order, double amount) {
		return jdbcTemplate.update(SqlQueries.UPDATE_ORDER_AMOUNT, amount, order.getOrderId());
	}

	@Override
	public int updateStatus(Orders order, String status) {
		return jdbcTemplate.update(SqlQueries.UPDATE_ORDER_STATUS, status, order.getOrderId());
	}

	@Override
	public Orders getOrderById(int id) {
//		try {
//			return jdbcTemplate.queryForObject(SqlQueries.ORDER_BY_ID, new OrdersRowMapper(), id);
//		} catch (EmptyResultDataAccessException ex) {
//			return null;
//		}
		OrdersSearch order = new OrdersSearch();
		order.setOrderId(id);
		List<Orders> orders = getOrders(order);
		if (orders.size() == 1) {
			return orders.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Orders> getAllOrders() {
		// return jdbcTemplate.query(SqlQueries.ALL_ORDERS, new OrdersRowMapper());
		OrdersSearch order = new OrdersSearch();
		return getOrders(order);
	}

	@Override
	public int getOrderId(Orders order) {
//		try {
//			return jdbcTemplate.queryForObject(SqlQueries.GET_ORDERID, Integer.class,
//					new Object[] { order.getCustomerId(), order.getWaiterId() });
//		} catch (EmptyResultDataAccessException ex) {
//			return 0;
//		}
		OrdersSearch orderS = new OrdersSearch();
		orderS.setCustomerId(order.getCustomerId());
		orderS.setWaiterId(order.getWaiterId());
		orderS.setStatuses(List.of("Pending"));
		List<Orders> orders = getOrders(orderS);
		if (orders.size() == 1) {
			return orders.get(0).getOrderId();
		} else {
			return 0;
		}
	}

	@Override
	public List<Orders> getOrdersByCategory(String category) {
//		return jdbcTemplate.query(SqlQueries.ORDERS_BY_CATEGORY, new OrdersRowMapper(), category);
		OrdersSearch order = new OrdersSearch();
		order.setStatuses(List.of(category));
		return getOrders(order);
	}

	private List<Orders> getOrders(OrdersSearch order) {

		Map<String, Object> params = new HashMap<>();

		params.put("hasOrderId", order.getOrderId() != null);
		params.put("hasCustomerId", order.getCustomerId() != null);
		params.put("hasWaiterId", order.getWaiterId() != null);
		params.put("hasStartDate", order.getStartDate() != null);
		params.put("hasEndDate", order.getEndDate() != null);
		params.put("hasAmount", order.getAmount() != null);
		params.put("hasStatuses", (order.getStatuses() != null && !order.getStatuses().isEmpty()));

		params.put("orderId", order.getOrderId());
		params.put("customerId", order.getCustomerId());
		params.put("waiterId", order.getWaiterId());
		params.put("startDate", order.getStartDate());
		params.put("endDate", order.getEndDate());
		params.put("amount", order.getAmount());
		params.put("statuses", order.getStatuses());

		return namedParameterJdbcTemplate.query(SqlQueries.ORDERS, params, new OrdersRowMapper());
	}

	@Override
	public void insertLog(Orders order) {
		jdbcTemplate.update(SqlQueries.ORDER_LOG, order.getOrderId(), order.getCustomerId(), order.getWaiterId(),
				Date.valueOf(order.getOrderDate()), order.getAmount(), order.getStatus().getStatus());
	}

}
