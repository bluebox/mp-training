package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.custom_classes.WaiterDetailsSelector;
import com.rms.restaurant_management_system_backend.dao.WaitersDao;
import com.rms.restaurant_management_system_backend.domain.Waiters;
import com.rms.restaurant_management_system_backend.rowmappers.WaiterDetailsRowMapper;
import com.rms.restaurant_management_system_backend.rowmappers.WaiterRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class WaitersDaoImpl implements WaitersDao {

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public WaitersDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int insertWaiter(int employeeId) {
		String insertQuery = SqlQueries.WAITER_INSERT;
		int count = jdbcTemplate.update(insertQuery, employeeId);
		return count;
	}

	@Override
	public int updateWaiterAvailability(int waiterId, String availability) {
		String updateQuery = SqlQueries.WAITER_UPDATE_AVAILABILITY;
		int count = jdbcTemplate.update(updateQuery, availability, waiterId);
		return count;
	}

	@Override
	public int insertWaiterLog(Waiters oldWaiter) {
		String insertQuery = SqlQueries.WAITER_LOG_INSERT;
		System.out.print(oldWaiter);
		int count = jdbcTemplate.update(insertQuery, oldWaiter.getWaiterId(), oldWaiter.getEmployeeId(),
				oldWaiter.getAvailability().getDbName());
		return count;
	}

	@Override
	public int selectAssignedOrdersCount(int waiterId) {
//		String selectQuery = SqlQueries.COUNT_WAITER_ASSIGNED_ORDERS;
//		int count = jdbcTemplate.queryForObject(selectQuery, Integer.class, waiterId);
//		return count;
		Map<String, Object> params = new HashMap<>();
		params.put("waiterId", waiterId);
		return namedParameterJdbcTemplate.queryForObject(SqlQueries.COUNT_ORDERS, params, Integer.class);
	}

	@Override
	public Waiters selectWaiterById(int waiterId) {
		String selectQuery = SqlQueries.WAITER_SELECT_BY_ID;
		return jdbcTemplate.query(selectQuery, new WaiterRowMapper(), waiterId).stream().findFirst().orElse(null);
	}

	@Override
	public List<WaiterDetailsSelector> selectAvailableWaiters() {
		String selectQuery = SqlQueries.WAITER_SELECT_AVAILABLE;
		List<WaiterDetailsSelector> availableWaiters = jdbcTemplate.query(selectQuery, new WaiterDetailsRowMapper());
		return availableWaiters;
	}

	@Override
	public int deleteWaiterByEmpId(int employeeId) {
		String deleteQuery = SqlQueries.WAITER_DELETE_BY_EMP_ID;
		int count = jdbcTemplate.update(deleteQuery, employeeId);
		return count;
	}

	@Override
	public Waiters selectWaiterByEmpId(int employeeId) {
		String selectQuery = SqlQueries.WAITER_SELECT_BY_EMP_ID;
		return jdbcTemplate.query(selectQuery, new WaiterRowMapper(), employeeId).stream().findFirst().orElse(null);
	}

}
